package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Compra ENTIDAD QUE CINTIENE LOS DATOS
 * DEL USUARIO Y TIPO DE PAGO CUANDO SE REALIZA UNA COMPRA
 */

@Entity
@Table(name = "Compras")

@NamedQueries({
		@NamedQuery(name = Compra.PRODUCTOS_COMPRA_POR_ID, query = "select detalles.producto from Compra co INNER JOIN co.detallesCompra detalles  where detalles.compra.idCompra=:id"),
		@NamedQuery(name = Compra.TOTAl_COMPRAS_UNICAS, query = "select count(c) from Compra c"),
		@NamedQuery(name = Compra.TOTAL_GASTO_EN_COMPRAS_POR_USUARIO, query = "select new co.edu.uniquindio.grid.dto.UsuarioGastosComprasDTO(c.usuario.nombreCompleto,sum(dc.precioVenta * dc.cantidadProducto)) From Compra c INNER JOIN c.detallesCompra dc where c.usuario.cedula=:id"),
		@NamedQuery(name = Compra.COMPRA_FECHAS_METODO_PAGO, query = "SELECT c FROM Compra c WHERE c.fechaCompra BETWEEN :inicial AND :final AND c.tipoPago=:tipo"),
        @NamedQuery(name = Compra.COMPRAS_DETALLES_POR_PERSONA,query = "SELECT dc,c  FROM  Compra c INNER JOIN c.detallesCompra dc where c.usuario.cedula=:cedula")
		
})
public class Compra implements Serializable {

	/**
	 * Cree una consulta que devuelva una lista de Compras hechas entre dos fechas
	 * determinadas y que además hayan sido pagadas por un método de pago
	 * específico.
	 */
	public static final String COMPRA_FECHAS_METODO_PAGO = "compra_fechas_por_metodo_pago";

	/**
	 * onsulta que devuelva las compras hechas por una persona, la consulta debe
	 * devolver también el detalle de cada compra.
	 */

	public static final String COMPRAS_DETALLES_POR_PERSONA = "compra_detalles_por_persona";
	
	public static final String TOTAL_GASTO_EN_COMPRAS_POR_USUARIO = "total_gasto_en_compras";

	public static final String TOTAl_COMPRAS_UNICAS = "compras_unicas";

	public static final String PRODUCTOS_COMPRA_POR_ID = "producto_compra";

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
	@Column(name = "FECHA_COMPRA", nullable = false)
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
