package com.example.fooddelivery.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "SHIPPING_ADDRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "SHIPPING_TYPE", discriminatorType = DiscriminatorType.STRING)
public class ShippingAddress extends Address implements Serializable {

}
