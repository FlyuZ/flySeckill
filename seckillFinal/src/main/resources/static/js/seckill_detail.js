// JavaScript模块化
var seckill = {
    //封装秒杀相关的ajax的url地址
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function(seckillId){
            return '/seckill/' + seckillId + '/exposer';
        },
        execution : function(seckillId, md5){
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    //处理秒杀逻辑
    handleSeckill: function(seckillId, node){
        //获取秒杀地址，控制显示逻辑，执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function(result){
            //在回调函数中执行交互流程
            if (result && result['success']){
                var exposer = result['data'];
                if (exposer['exposed']){
                    //开启秒杀
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log('killUrl:' + killUrl);
                    //one: 绑定一次点击事件
                    $('#killBtn').one('click', function(){
                        //执行秒杀的操作
                        //1. 先禁用按钮
                        $(this).addClass('disabled');
                        //2. 发送秒杀请求，执行秒杀
                        $.post(killUrl, {}, function(result){
                            if (result && result['success']){
                                var killResult = result['data'];
                                var stateInfo = killResult['stateInfo'];
                                //3. 显示秒杀结果
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        })
                    });
                    node.show();
                } else{
                    //未开启秒杀，避免用户得到的时间有偏差
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countdown(seckillId, now, start, end);
                }
            } else{
                console.log('result:' + result);
            }
        });
    },
    //计时
    countdown: function (seckillId, nowTime, startTime, endTime ) {
        var seckillBox = $('#seckill-box');
        var seckillTimeSpan = $('#seckill-time-span');
        //时间判断
        if (nowTime > endTime){
            //秒杀结束
            seckillTimeSpan.html('秒杀结束');
            seckillBox.hide();
        }else if(nowTime < startTime){
            //说明秒杀未开始，计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillTimeSpan.countdown(killTime, function(event){
                //时间格式
                var format = event.strftime('秒杀开始倒计时： %D天 %H时 %M分 %S秒');
                seckillTimeSpan.html(format);
                //时间完成后回调事件
            }).on('finish.countdown', function(){
                //获取秒杀地址，控制实现逻辑，执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        }else{
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);

            //计时
            var killEndTime = new Date(endTime + 1000);
            seckillTimeSpan.countdown(killEndTime, function(event){
                //时间格式
                var format = event.strftime('距离秒杀结束： %D天 %H时 %M分 %S秒');
                seckillTimeSpan.html(format);
            });
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            var user=params['user'];
            if(user == null)
            {
                window.location.href="/seckill/loginpage";
            }

            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });
        }
    }
};