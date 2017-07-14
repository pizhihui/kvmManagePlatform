package com.yonyou.findata.model;

/**
 *  城市vo
 * @author: pizhihui
 * @datae: 2017-05-05
 */
public class City {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String state;

    private String country;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getState() + "," + getCountry();
    }

}
