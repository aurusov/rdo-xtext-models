package domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sparepartsrequests")
public class Order {

	@Id
	@Column(name = "ID")
	public int id;

	@Column(name = "Status")
	public String status;

	@Column(name = "SolutionName")
	public String solutionName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateOfCreation")
	public Calendar dateOfCreation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateOfProcessing")
	public Calendar dateOfProcessing;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateOfRealization")
	public Calendar dateOfRealization;

	@Column(name = "OrderNumber")
	public int orderNumber;

	@Column(name = "OutfitNumber")
	public int outfitNumber;

	@Column(name = "DiagnosticOrderNumber")
	public int diagnosticOrderNumber;

	@Column(name = "DiagnosticOutfitNumber")
	public int diagnosticOutfitNumber;

	@Column(name = "Selected")
	public boolean selected;

	@Column(name = "MustBeOrdered")
	public boolean mustBeOrdered;

	@Column(name = "Shipped")
	public boolean shipped;

	@Column(name = "TypeOfPrice")
	public int typeOfPrice;

	@Column(name = "ResponsibleForSelection")
	public String responsibleForSelection;

	@Column(name = "ResponsibleForOrdering")
	public String responsibleForOrdering;

	@Column(name = "ResponsibleForShipping")
	public String responsibleForShipping;

	@Column(name = "PartsRecipient")
	public String partsRecipient;

	@Column(name = "Description")
	public String description;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	public List<PartRequest> requestedParts;

	public LocalDate getDateOfCreation() {
		return toLocalDate(dateOfCreation);
	}

	public long getEpochDayOfCreation() {
		return getDateOfCreation().toEpochDay();
	}

	public LocalDate getDateOfProcessing() {
		return toLocalDate(dateOfProcessing);
	}

	public LocalDate getDateOfRealization() {
		return toLocalDate(dateOfRealization);
	}

	private LocalDate toLocalDate(Calendar calendar) {
		return calendar == null ? null
				: LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public String toString() {
		return "Order [id=" + id + "]";
	}
}
