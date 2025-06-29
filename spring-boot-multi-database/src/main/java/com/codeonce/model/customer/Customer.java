package com.codeonce.model.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_customer_tab")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	@Id
	private Integer id;
	private String email;
	private String cname;
}
