package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.security.enterprise.identitystore.IdentityStore.ValidationType;

/**
 * Entity implementation class for Entity: Compra
 *
 */

@Entity
@Table(name = "Compras")
public class Compra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMPRA")
	private int idCompra;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PAGO")
	private TipoPago tipoPago;
	
	
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "compra")
	private List<DetalleCompra> detallesCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_COMPRA")
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


}
