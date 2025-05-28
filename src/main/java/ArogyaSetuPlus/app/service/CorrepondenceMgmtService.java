package ArogyaSetuPlus.app.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ArogyaSetuPlus.app.bindings.CoTriggerSummary;
import ArogyaSetuPlus.app.email.EmailUtils;
import ArogyaSetuPlus.app.entity.CitizenRegistrationEntity;
import ArogyaSetuPlus.app.entity.CoTriggersEntity;
import ArogyaSetuPlus.app.entity.EligibilityDetailsEntity;
import ArogyaSetuPlus.app.repository.ICoTriggerRepository;
import ArogyaSetuPlus.app.repository.IEligibilityDeterminationRepository;

@Service
public class CorrepondenceMgmtService implements ICorrespondenceMgmtService {
    @Autowired
    private ICoTriggerRepository triggerRepo;
    @Autowired
    private IEligibilityDeterminationRepository eligiblityRepo;
    @Autowired
    private IAppRegRepository citizenRepo;
    @Autowired
    private EmailUtils emailUtils;

    @Override
    public CoTriggerSummary processPendingTriggers() throws Exception {
	List<CoTriggersEntity> triggerEntities = triggerRepo.findByTriggerStatus("Pending");
	int successTrigger = 0;
	int pendingTrigger = 0;

	for (CoTriggersEntity entity : triggerEntities) {
	    // Fetch eligibility details
	    EligibilityDetailsEntity eligibleEntity = eligiblityRepo.findByAppId(entity.getAppId());
	    if (eligibleEntity == null) {
		System.err.println("No EligibilityDetailsEntity found for appId: " + entity.getAppId());
		pendingTrigger++;
		continue;
	    }

	    // Fetch citizen details
	    Optional<CitizenRegistrationEntity> optCitizen = citizenRepo.findByAppId(entity.getAppId());
	    CitizenRegistrationEntity citizenEntity = optCitizen.orElse(null);
	    if (citizenEntity == null) {
		System.err.println("No CitizenRegistrationEntity found for appId: " + entity.getAppId());
		pendingTrigger++;
		continue;
	    }

	    try {
		generatePDFandSendMail(eligibleEntity, citizenEntity);
		successTrigger++;
	    } catch (Exception e) {
		pendingTrigger++;
		System.err.println("Exception: " + e.getMessage());
	    }
	}

	// Create summary report
	CoTriggerSummary summary = new CoTriggerSummary();
	summary.setTotalTriggers(triggerEntities.size());
	summary.setPendingTriggers(pendingTrigger);
	summary.setSuccessTriggers(successTrigger);
	return summary;
    }

    private void generatePDFandSendMail(EligibilityDetailsEntity eligibleEntity,
	    CitizenRegistrationEntity citizenEntity) throws Exception {

	Document document = new Document(PageSize.A4);
	File file = new File(eligibleEntity.getPlanHolderName() + "_" + eligibleEntity.getAppId() + ".pdf");
	FileOutputStream fos = new FileOutputStream(file);
	PdfWriter.getInstance(document, fos);
	document.open();

	Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
	font.setSize(24);
	font.setColor(Color.CYAN);

	//creating a paragraph
	Paragraph para = new Paragraph("Plan Approval / Denial Communication", font);
	para.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(para);

	PdfPTable table = new PdfPTable(10);
	table.setWidthPercentage(70);
	table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
	table.setSpacingBefore(2.0f);

	PdfPCell cell = new PdfPCell();
	cell.setBackgroundColor(Color.gray);
	cell.setPadding(5);
	Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	cellFont.setColor(Color.BLACK);

	cell.setPhrase(new Phrase("Application No", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Trace Id", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Plan Holder Name", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Holder CHII Number ", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Plan Name", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Plan Status", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Plan Start Date", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Plan End Date", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Benifit Amount", cellFont));
	table.addCell(cell);
	cell.setPhrase(new Phrase("Denial Reason", cellFont));
	table.addCell(cell);

	table.addCell(String.valueOf(eligibleEntity.getAppId()));
	table.addCell(String.valueOf(eligibleEntity.getEdTraceId())); // Missing Trace ID
	table.addCell(eligibleEntity.getPlanHolderName());
	table.addCell(eligibleEntity.getChiiNumber()); // No need for String.valueOf()
	table.addCell(eligibleEntity.getPlanCode());
	table.addCell(eligibleEntity.getPlanStatus());
	table.addCell(String.valueOf(eligibleEntity.getPlanStartDate()));
	table.addCell(String.valueOf(eligibleEntity.getPlanEndDate()));
	table.addCell(String.valueOf(eligibleEntity.getBenifitAmount()));
	table.addCell(eligibleEntity.getDenialReason() == null ? "N/A" : eligibleEntity.getDenialReason());

	document.add(table);
	System.out.println(document.toString());
	document.close();

	//sending Mail
	String email = citizenEntity.getEmail();
	String subject = "ArogyaSetuPlus - Plan Approval / Denial Status";
	String body = String.format("Hi %s,\n\n" + "Greetings from ArogyaSetuPlus!\n\n"
		+ "We have received an insurance claim request from CHII Number: %s.\n"
		+ "Status of your plan (%s): %s.\n\n"
		+ "Please find the attached document for further details, including plan duration, denial reasons, and more.\n\n"
		+ "Best Regards,\n" + "ArogyaSetuPlus Team", eligibleEntity.getPlanHolderName(),
		eligibleEntity.getChiiNumber(), eligibleEntity.getPlanCode(), eligibleEntity.getPlanStatus());
	emailUtils.sendMail(email, subject, body, file);
	updateCoTrigger(eligibleEntity.getAppId(), file);

    }

    @SuppressWarnings("resource")
    private void updateCoTrigger(Integer appId, File file) throws Exception {
	CoTriggersEntity triggerEntity = triggerRepo.findByAppId(appId);
	byte[] pdfContent = new byte[(int) file.length()];
	FileInputStream fis = new FileInputStream(file);
	if (!file.exists() || file.length() == 0) {
	    throw new RuntimeException("PDF File was not created properly.");
	}
	System.out.println("PDF successfully created: " + file.getAbsolutePath());

	fis.read(pdfContent);
	if (triggerEntity != null) {
	    triggerEntity.setCoNoticePdf(pdfContent);
	    System.out.println(pdfContent.toString());
	    triggerEntity.setTriggerStatus("Completed");
	    triggerRepo.save(triggerEntity);
	}
	fis.close();

    }

}
