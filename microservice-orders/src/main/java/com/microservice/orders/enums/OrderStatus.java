package com.microservice.orders.enums;

public enum OrderStatus {
    COMPLETED(1), // Representa "Completed"
    PENDING(2),   // Representa "Pending"
    CANCELLED(3); // Representa "Cancelled"

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
