package thefaco.beverage.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.domain.BeverageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExcelController {

    private final BeverageRepository beverageRepository;

    //excel.html 이랑 매핑
    @GetMapping("/excel")
    public String main(){
        return "excel";
    }

    @PostMapping("/excel/read")
    public String readExcel(@RequestParam("file") MultipartFile file, Model model)
    throws IOException {
        //리스트 생성
        List<Beverage> dataList = new ArrayList<>();
        //파일 확장자 체크
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        Workbook workbook = null;

        if(!extension.equals("xlsx") && !extension.equals("xls")){
            throw new IOException("엑셀파일만 업로드 해주세요");
        } else {
            workbook = new XSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        //엑셀 행,열 읽어오기
        for(int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++){
            Row row = worksheet.getRow(i);

            Beverage beverage = new Beverage();
            beverage.setId((long) row.getCell(0).getNumericCellValue());
            beverage.setName(row.getCell(1).getStringCellValue());
            beverage.setPrice((int)row.getCell(2).getNumericCellValue());
            beverage.setSize((int)row.getCell(3).getNumericCellValue());
            beverage.setType(row.getCell(4).getStringCellValue());

            dataList.add(beverage);
        }

        model.addAttribute("datas", dataList);

        return "excellist";
    }
}
