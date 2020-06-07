package com.chequp.admin.model;

/**
 * Created by mukul on 5/25/2019.
 */

public class ServiceWithBoolean {
    boolean isSelected;
    ServiceNameInfo serviceName;
    Integer fees ;

    public ServiceWithBoolean() {
    }

    public ServiceWithBoolean(boolean isSelected, ServiceNameInfo serviceName, Integer fees) {
        this.isSelected = isSelected;
        this.serviceName = serviceName;
        this.fees = fees;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ServiceNameInfo getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceNameInfo serviceName) {
        this.serviceName = serviceName;
    }
}
