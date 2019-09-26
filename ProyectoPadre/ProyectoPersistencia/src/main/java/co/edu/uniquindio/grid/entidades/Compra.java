package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compra
 * ENTIDAD QUE CINTIENE LOS DATOS DEL USUARIO Y TIPO DE PAGO CUANDO SE REALIZA UNA COMPRA
 */

@Entity
@Table(name = "Compras")

//@NamedQuery(name = Compra.PRODUCTOS_COMPRA,query = "select  d.PRODUCTO_ID_PRODUCTO,d. from Compras c")
public class Compra implements Serializable {

	
	
	public static final String PRODUCTOS_COMPRA="producto_compra";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPRA")
	private int idCompra;

	@Column(name = "TIPO_PAGO")
	@Enumerated(EnumType.STRING)
	private TipoPago tipoPago;
	
	
	@ManyToOne
	private Usuario usuario;

	
	@OneToMany(mappedBy = "compra")
	private List<DetalleCompra> detallesCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_COMPRA",nullable = false)
	private Date fechaCompra;


	private static final long serialVersionUID = 1L;

	public Compra() {
		super();
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleCompra> getDetallesCompra() {
		return detallesCompra;
	}

	public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
		this.detallesCompra = detallesCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCompra;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (idCompra != other.idCompra)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", tipoPago=" + tipoPago + ", usuario=" + usuario + ", detallesCompra="
				+ detallesCompra + ", fechaCompra=" + fechaCompra + "]";
	}
	
	


}
