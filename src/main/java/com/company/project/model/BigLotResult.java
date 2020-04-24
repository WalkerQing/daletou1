package com.company.project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "big_lot_result")
public class BigLotResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_of_periods")
    private String numOfPeriods;

    @Column(name = "red_balls")
    private String redBalls;

    @Column(name = "blue_balls")
    private String blueBalls;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "open_time")
    private Date openTime;

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
     * @return num_of_periods
     */
    public String getNumOfPeriods() {
        return numOfPeriods;
    }

    /**
     * @param numOfPeriods
     */
    public void setNumOfPeriods(String numOfPeriods) {
        this.numOfPeriods = numOfPeriods;
    }

    /**
     * @return red_balls
     */
    public String getRedBalls() {
        return redBalls;
    }

    /**
     * @param redBalls
     */
    public void setRedBalls(String redBalls) {
        this.redBalls = redBalls;
    }

    /**
     * @return blue_balls
     */
    public String getBlueBalls() {
        return blueBalls;
    }

    /**
     * @param blueBalls
     */
    public void setBlueBalls(String blueBalls) {
        this.blueBalls = blueBalls;
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

    /**
     * @return open_time
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * @param openTime
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public BigLotResult(String numOfPeriods, String redBalls, String blueBalls, Date createTime, Date openTime) {
        this.numOfPeriods = numOfPeriods;
        this.redBalls = redBalls;
        this.blueBalls = blueBalls;
        this.createTime = createTime;
        this.openTime = openTime;
    }

    public BigLotResult() {
    }
}