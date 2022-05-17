package example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "tên sản phẩm không được để trống")
	@NotNull(message = "tên sản phẩm không được null")
	@Column(name = "nameproduct")
	private String nameProduct;
	@Column(name = "category")
	@NotNull(message = "category không được null")
	private String category;
	@Min(value = 1, message = "đơn giá phải lớn hơn 1")
	@Column(name = "unitprice")
	private double unitPrice;
	@Min(message = " số lượng sản phẩm phải lớn hơn 1", value = 1)
	@Column(name = "amount")
	private int amount;


}
