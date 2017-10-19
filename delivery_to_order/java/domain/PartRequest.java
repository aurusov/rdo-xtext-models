package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "requestedpartslist")
@IdClass(value = PartRequest.PartRequestId.class)
public class PartRequest {

	@Id
	@ManyToOne
	@JoinColumn(name = "RequestID")
	public Order order;

	@Id
	@ManyToOne
	@JoinColumn(name = "SparePartID")
	public Part part;

	/**
	 * Цена продажи в момент формирования заказа. Учитывать не нужно
	 */
	@Column(name = "Price")
	public int price;

	/**
	 * Дата доставки, если {@code NULL}, то доставки не было
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DateOfDelivery")
	public Calendar dateOfDelivery;

	@Column(name = "Count")
	public int count;

	public LocalDate getDateOfDelivery() {
		return LocalDate.of(dateOfDelivery.get(Calendar.YEAR), dateOfDelivery.get(Calendar.MONTH),
				dateOfDelivery.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * @return длительность доставки в днях
	 */
	public Long getDeliveryInterval() {
		if (!hasDeliveryInterval())
			throw new NullPointerException("Undefined creation or delivery date");
		LocalDate start = order.getDateOfCreation();
		LocalDate end = getDateOfDelivery();
		return start.until(end, ChronoUnit.DAYS);
	}
	
	/**
	 * @return определена ли длительность доставки (т.е. заказ был отгружен)
	 */
	public boolean hasDeliveryInterval() {
		return dateOfDelivery != null && order.dateOfCreation != null;
	}

	@Override
	public String toString() {
		return "PartRequest [Part=" + part.name + ", DeliveryInterval=" + getDeliveryInterval() + ", Count=" + count
				+ "]";
	}

	@SuppressWarnings("serial")
	public static class PartRequestId implements Serializable {
		public Order order;
		public Part part;
	}
}
