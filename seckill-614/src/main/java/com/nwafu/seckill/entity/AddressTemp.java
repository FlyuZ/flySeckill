package com.nwafu.seckill.entity;

public class AddressTemp {
    private Integer addressId;
    private String addressText;
    private String phone;
    private String name;

    public AddressTemp(Integer addressId, String addressText, String phone, String name) {
        this.addressId = addressId;
        this.addressText = addressText;
        this.phone = phone;
        this.name = name;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddressTemp{" +
                "addressId=" + addressId +
                ", phone='" + phone + '\'' +
                ", addressText='" + addressText + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
