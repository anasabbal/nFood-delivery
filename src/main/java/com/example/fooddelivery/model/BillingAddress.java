package com.example.fooddelivery.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "BILLING_ADRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BILLING_TYPE", discriminatorType = DiscriminatorType.STRING)
public class BillingAddress extends Address  {
}
