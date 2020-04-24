package com.company.project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Roll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "red_ball")
    private String redBall;

    @Column(name = "blue_ball")
    private String blueBall;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return red_ball
     */
    public String getRedBall() {
        return redBall;
    }

    /**
     * @param redBall
     */
    public void setRedBall(String redBall) {
        this.redBall = redBall;
    }

    /**
     * @return blue_ball
     */
    public String getBlueBall() {
        return blueBall;
    }

    /**
     * @param blueBall
     */
    public void setBlueBall(String blueBall) {
        this.blueBall = blueBall;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Roll() {
    }

    public Roll(String redBall, String blueBall, Date createTime) {
        this.redBall = redBall;
        this.blueBall = blueBall;
        this.createTime = createTime;
    }
}