package example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "tên sản phẩm không được null")
	@NotEmpty(message = "tên sản phẩm không được để trống")
	@Column(name = "nameproduct")
	private String nameProduct;
	@NotNull(message = "don gia không được null")
	@Min(1)
	@Column(name = "unitprice")
	private double unitPrice ;
	@Min(1)
	@Column(name = "amount")
	private int amount;
	
}
