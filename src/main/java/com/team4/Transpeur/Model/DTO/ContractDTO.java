package com.team4.Transpeur.Model.DTO;

import com.team4.Transpeur.Model.Entities.Contract;

public class ContractDTO {
    private Long travelScheduleId;
    private Long receiverId;
    private String description;
    private Long shipperId;
    private Float price;
    public ContractDTO(){}
    public ContractDTO(Contract contract) {
        this.shipperId = contract.getTravelSchedule().getUser().getId();
        this.travelScheduleId = contract.getTravelSchedule().getId();
        this.receiverId = contract.getReceiver().getId();
        this.description = contract.getDescription();
        this.price = contract.getPrice();
    }
    public ContractDTO(Long shipperId, Long travelScheduleId, Long receiverId, String description, Float price) {
        this.travelScheduleId = travelScheduleId;
        this.receiverId = receiverId;
        this.description = description;
        this.shipperId = shipperId;
        this.price = price;
    }

    public Long getTravelScheduleId() {
        return travelScheduleId;
    }

    public void setTravelScheduleId(Long travelScheduleId) {
        this.travelScheduleId = travelScheduleId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getDescription() {
        return description;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
