package com.tiger.worker.cases.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class PdfUtils {

  public static void main(String[] args) throws IOException, DocumentException {
    HashMap map = new HashMap<String, String>();
    map.put("fill_1","AAA8888");
    map.put("fill_2","张小明");
    map.put("fill_3","保时捷上海金桥分公司");
    map.put("fill_4","2020");
    map.put("fill_5","01");
    map.put("fill_6","20");
    map.put("fill_24","张小明");
    map.put("fill_25","上海陆家嘴中心100号");
    map.put("fill_26","Porsche 911");
    map.put("fill_27","13588182456");
    map.put("fill_29","nb@nb.com");
    map.put("toggle_4","On");
    map.put("fill_15","42062219900101XXXX");
    map.put("per_card_type_2","On");
    map.put("fill_39","保时捷");
    map.put("fill_40","911");
    map.put("fill_41","VIN111");
    map.put("fill_42","保时捷中国");
    map.put("fill_43","中国-上海");
    map.put("fill_44","AK477");
    map.put("fill_45","波尔多红");
    map.put("fill_46","高级黑");
    map.put("fill_47","1235667");
    map.put("fill_48","HGZ001");
    map.put("fill_49","劳斯莱斯同款雨伞，法拉利同款钥匙扣，兰博基尼同款靠枕");
    map.put("fill_50","6223100.00");
    map.put("fill_7","6348100.00");
    map.put("baiwan_unit","陆");
    map.put("fill_9","叁");
    map.put("fill_10","肆");
    map.put("fill_11","玐");
    map.put("fill_12","壹");
    map.put("fill_13","零");
    map.put("fill_14","零");
//        String path = PdfUtils.class.getResource("/template").getPath();
//        System.out.println("path:"+path);
//        String sourceFile = path + File.separator + "test.pdf";
    String sourceFile = "D:\\汽车订制购买合同模板.pdf";
    String targetFile = "D:/测试合同.pdf";
    genPdf(map,sourceFile,targetFile);

//        System.out.println("获取pdf表单中的fieldNames:"+getTemplateFileFieldNames(sourceFile));
//        System.out.println("读取文件数组:"+fileBuff(sourceFile));
//        System.out.println("pdf转图片:"+pdf2Img(new File(targetFile),imageFilePath));
  }

  private static void genPdf(HashMap map, String sourceFile, String targetFile)
      throws IOException, DocumentException {
    File templateFile = new File(sourceFile);
    fillParam(map, FileUtils.readFileToByteArray(templateFile), targetFile);
  }


  /**
   * Description: 使用map中的参数填充pdf，map中的key和pdf表单中的field对应 <br>
   * @author mk
   * @Date 2018-11-2 15:21 <br>
   * @Param
   * @return
   */
  public static void fillParam(Map<String, String> fieldValueMap, byte[] file, String contractFileName) {
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(contractFileName);
      PdfReader reader = null;
      PdfStamper stamper = null;
      BaseFont base = null;
      try {
        reader = new PdfReader(file);
        stamper = new PdfStamper(reader, fos);
        stamper.setFormFlattening(true);
        base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        AcroFields acroFields = stamper.getAcroFields();
        for (String key : acroFields.getFields().keySet()) {
          acroFields.setFieldProperty(key, "textfont", base, null);
          acroFields.setFieldProperty(key, "textsize", new Float(9), null);
        }
        if (fieldValueMap != null) {
          for (String fieldName : fieldValueMap.keySet()) {
            acroFields.setField(fieldName, fieldValueMap.get(fieldName));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (stamper != null) {
          try {
            stamper.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        if (reader != null) {
          reader.close();
        }
      }

    } catch (Exception e) {
      System.out.println("填充参数异常");
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(fos);
    }
  }

  /**
   * Description: 获取pdf表单中的fieldNames<br>
   * @author mk
   * @Date 2018-11-2 15:21 <br>
   * @Param
   * @return
   */
  public static Set<String> getTemplateFileFieldNames(String pdfFileName) {
    Set<String> fieldNames = new TreeSet<String>();
    PdfReader reader = null;
    try {
      reader = new PdfReader(pdfFileName);
      Set<String> keys = reader.getAcroFields().getFields().keySet();
      for (String key : keys) {
        int lastIndexOf = key.lastIndexOf(".");
        int lastIndexOf2 = key.lastIndexOf("[");
        fieldNames.add(key.substring(lastIndexOf != -1 ? lastIndexOf + 1 : 0, lastIndexOf2 != -1 ? lastIndexOf2 : key.length()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }

    return fieldNames;
  }

}
