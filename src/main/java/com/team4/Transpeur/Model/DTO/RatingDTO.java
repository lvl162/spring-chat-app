package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Rating;

public class RatingDTO {
    public RatingDTO(Rating rating) {
        this.fromId = rating.getContract().getReceiver().getId();
        this.toId = rating.getContract().getTravelSchedule().getUser().getId();
        this.atContractId = rating.getContract().getId();
        this.star = rating.getStar();
        this.description =rating.getDescription();

    }
    private Long fromId;
    private Long toId;
    private Long atContractId;
    private String description;
    private Integer star;
   /*
   "fromId": 123,
    "toId" : 12121,
    "atContractId" : 1212,
    "description" : "ship wa cham, 1 diem ve cho"
    "star" : 3
   * */
    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getAtContractId() {
        return atContractId;
    }

    public void setAtContractId(Long atContractId) {
        this.atContractId = atContractId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
