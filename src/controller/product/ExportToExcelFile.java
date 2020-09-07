package controller.product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Product;
import service.LoginService;
import service.ProductService;

public class ExportToExcelFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExportToExcelFile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/product");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Product");
		
		XSSFCellStyle centerStyle = workbook.createCellStyle();
		centerStyle.setAlignment(HorizontalAlignment.CENTER);
		centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		XSSFCellStyle styleTD = workbook.createCellStyle();
		styleTD.setBorderBottom(BorderStyle.THIN);
		styleTD.setBorderLeft(BorderStyle.THIN);
		styleTD.setBorderRight(BorderStyle.THIN);
		styleTD.setBorderTop(BorderStyle.THIN);
		XSSFFont font = workbook.createFont();
		font.setFontName("Arial");  
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		styleTD.setFillBackgroundColor(IndexedColors.RED.getIndex());
		styleTD.setFillPattern(FillPatternType.THIN_BACKWARD_DIAG);
		styleTD.setAlignment(HorizontalAlignment.CENTER);
		styleTD.setVerticalAlignment(VerticalAlignment.CENTER);
		styleTD.setFont(font);
		
		XSSFCellStyle styleX = workbook.createCellStyle();
		XSSFFont fontX = workbook.createFont();
		fontX.setColor(IndexedColors.BLACK1.getIndex());
		fontX.setBold(true);
		styleX.setAlignment(HorizontalAlignment.CENTER);
		styleX.setVerticalAlignment(VerticalAlignment.CENTER);
		styleX.setFont(fontX);
		
		XSSFCellStyle styleDate = workbook.createCellStyle();
		XSSFFont fontDate = workbook.createFont();
		fontDate.setColor(IndexedColors.BLACK.getIndex());
		styleDate.setAlignment(HorizontalAlignment.RIGHT);
		styleDate.setVerticalAlignment(VerticalAlignment.CENTER);
		styleDate.setFont(fontDate);
		
		XSSFCellStyle styleNumber = workbook.createCellStyle(); 
		short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
		styleNumber.setDataFormat(format);
		
		int rowNum = 0;
		Row firstRow = sheet.createRow(rowNum++);
		Cell firstCell = firstRow.createCell(0);
		firstCell.setCellValue("CÔNG TY KINH DOANH THỜI TRANG MAY MẶC");
		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:C1"));
		Cell lastCell = firstRow.createCell(5);
		lastCell.setCellValue("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM");
		sheet.addMergedRegion(CellRangeAddress.valueOf("F1:G1"));
		firstCell.setCellStyle(styleX);
		lastCell.setCellStyle(styleX);
		
		Row secondRow = sheet.createRow(rowNum++);
		Cell cellA2 = secondRow.createCell(0);
		cellA2.setCellValue("---------------------------");
		sheet.addMergedRegion(CellRangeAddress.valueOf("A2:C2"));
		Cell cellF2 = secondRow.createCell(5);
		cellF2.setCellValue("Độc lập - Tự do - Hạnh phúc");
		sheet.addMergedRegion(CellRangeAddress.valueOf("F2:G2"));
		cellA2.setCellStyle(styleX);
		cellF2.setCellStyle(styleX);
		
		Row thirdRow = sheet.createRow(rowNum++);
		Cell cellF3 = thirdRow.createCell(5);
		cellF3.setCellValue("---------------------");
		sheet.addMergedRegion(CellRangeAddress.valueOf("F3:G3"));
		cellF3.setCellStyle(styleX);
		
		Calendar cal = Calendar.getInstance();
		Row fourRow = sheet.createRow(rowNum++);
		Cell cellF4 = fourRow.createCell(5);
		cellF4.setCellValue("Hà Nội, ngày "+ cal.get(Calendar.DAY_OF_MONTH) +" tháng "+ (cal.get(Calendar.MONTH) + 1) +" năm "+cal.get(Calendar.YEAR));
		sheet.addMergedRegion(CellRangeAddress.valueOf("F4:G4"));
		cellF4.setCellStyle(styleDate);
		
		rowNum++;
		Row sixRow = sheet.createRow(rowNum++);
		Cell cellAll = sixRow.createCell(0);
		cellAll.setCellValue("DANH SÁCH SẢN PHẨM");
		sheet.addMergedRegion(CellRangeAddress.valueOf("A6:G6"));
		cellAll.setCellStyle(styleX);
		rowNum++;
		
		// Tạo tiêu đề cột
		Row td = sheet.createRow(rowNum++);
		Cell c1 = td.createCell(0);
		c1.setCellValue("ID");
		Cell c2 = td.createCell(1);
		c2.setCellValue("Danh mục");
		Cell c3 = td.createCell(2);
		c3.setCellValue("Tên sản phẩm");
		Cell c4 = td.createCell(3);
		c4.setCellValue("Giá");
		Cell c5 = td.createCell(4);
		c5.setCellValue("Số lượng");
		Cell c6 = td.createCell(5);
		c6.setCellValue("Nhà cung cấp");
		Cell c7 = td.createCell(6);
		c7.setCellValue("Trạng thái");
		c1.setCellStyle(styleTD);
		c2.setCellStyle(styleTD);
		c3.setCellStyle(styleTD);
		c4.setCellStyle(styleTD);
		c5.setCellStyle(styleTD);
		c6.setCellStyle(styleTD);
		c7.setCellStyle(styleTD);
		
		Connection conn = LoginService.getStoredConnection(request);
		List<Product> listPr = new ArrayList<>();
		try {
			listPr = ProductService.queryAllProduct(conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Product pr : listPr) {
			Row row = sheet.createRow(rowNum++);
			
			sheet.setColumnWidth(1,3000);
			sheet.setColumnWidth(2,6000);
			sheet.setColumnWidth(4,3000);
			sheet.setColumnWidth(5,7000);
			sheet.setColumnWidth(6,5000);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(pr.getPr_ID());
			cell1.setCellStyle(centerStyle);
			Cell cell2 = row.createCell(1);
			cell2.setCellValue(pr.getPr_CA_ID());
			cell2.setCellStyle(centerStyle);
			Cell cell3 = row.createCell(2);
			cell3.setCellValue(pr.getPr_Name());
			Cell cell4 = row.createCell(3);
			cell4.setCellStyle(styleNumber);
			cell4.setCellValue(pr.getPr_Price());
			Cell cell5 = row.createCell(4);
			cell5.setCellValue(pr.getPr_Quantify());
			cell5.setCellStyle(centerStyle);
			Cell cell6 = row.createCell(5);
			cell6.setCellValue(pr.getPr_SU_ID());
			Cell cell7 = row.createCell(6);
			String st = "";
			if(pr.getPr_Status().equals("1"))
				st = "Đang kinh doanh";
			else
				st = "Ngừng kinh doanh";
			cell7.setCellValue(st);
			cell7.setCellStyle(centerStyle);
			
			cell1.setCellStyle(style);
			cell2.setCellStyle(style);
			cell3.setCellStyle(style);
			cell4.setCellStyle(style);
			cell5.setCellStyle(style);
			cell6.setCellStyle(style);
			cell7.setCellStyle(style);
		}
		rowNum++;
		Row finishRow = sheet.createRow(rowNum++);
		Cell cellCf = finishRow.createCell(2);
		cellCf.setCellValue("QUẢN LÝ KHO HÀNG");
		Cell cellEf = finishRow.createCell(5);
		cellEf.setCellValue("NHÂN VIÊN KIỂM HÀNG");
		cellCf.setCellStyle(styleX);
		cellEf.setCellStyle(styleX);
		try {			
			response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=ListProduct.xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
