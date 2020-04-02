package com.secure.bankapp.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;

@Component("pdfView")
public class PDFBuilder extends AbstractITextPdfView {


	

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> viewModel, com.itextpdf.text.Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap model = (ModelMap) viewModel.get("model");
		
		List<Transaction> transactions = (List<Transaction>) model
				.get("transactions");
		
		UserDetail user = (UserDetail) model.get("user");
		
		Account account = (Account) model.get("account");

		doc.add(new Paragraph("Account Statement"));
		
		doc.add(new Paragraph("Customer Name: " + user.getFullName() ));
		
		doc.add(new Paragraph("Address: " + user.getAddress()));
		doc.add(new Paragraph("Account No: " + account.getAccountId()));
		doc.add(new Paragraph("Balance: " + account.getBalance() ));
	

		PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(10);

		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
		font.setColor(BaseColor.WHITE);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLACK);
		cell.setPadding(2);

		cell.setPhrase(new Phrase("Transactin Id", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Account", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Type", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Transaction Value", font));
		table.addCell(cell);
	
		for (Transaction transaction : transactions) {			
		
			table.addCell(String.valueOf(transaction.getId()));
			table.addCell(transaction.getTransactionDate().toString());
			if (transaction.getFromAccount().compareTo(account.getAccountId()) ==0) {
				table.addCell("xxxxxx" +String.valueOf( transaction.getToAccount()).substring(6));
				table.addCell(String.valueOf(Constants.TRANSACTION_TYPE.DEBIT.toString()));	
				table.addCell("-" + transaction.getTransactionValue());
			} else {
				table.addCell("xxxxxx" + String.valueOf(transaction.getFromAccount()).substring(6));
				table.addCell(String.valueOf(Constants.TRANSACTION_TYPE.CREDIT.toString()));
				table.addCell("+" + transaction.getTransactionValue());
			}
		

		}

		doc.add(table);

	}

}