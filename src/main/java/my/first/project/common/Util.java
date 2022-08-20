package my.first.project.common;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	   // 파일명 변경 메소드
	   public static String fileRename(String originFileName) {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	      String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

	      int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

	      String str = "_" + String.format("%05d", ranNum);

	      String ext = originFileName.substring(originFileName.lastIndexOf("."));

	      return date + str + ext;
	   }
	   
	   // 크로스 사이트 스트립트 공격을 방지 하기 위한 메소드
	   public static String XSSHandling(String content) {
	      if(content != null) {
	         content = content.replaceAll("&", "&amp;");
	         content = content.replaceAll("<", "&lt;");
	         content = content.replaceAll(">", "&gt;");
	         content = content.replaceAll("\"", "&quot;");
	      }
	      return content;
	   }

	   
	   // 크로스 사이트 스트립트 해제
	   public static String XSSClear(String content) {
	      if(content != null) {
	         content = content.replaceAll("&amp;", "&");
	         content = content.replaceAll("&lt;", "<" );
	         content = content.replaceAll("&gt;", ">");
	         content = content.replaceAll("&quot;", "\"");
	      }
	      return content;
	   }
	   
	   
	   
	   // 개행문자 처리 
	   public static String newLineHandling(String content) {
	      return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	   }
	   
	   // 개행문자 해제
	   public static String newLineClear(String content) {
	      return content.replaceAll("<br>", "\n");
	   }
	   
	   
	   // 썸머노트 첫번째 img태그에서 src만 불러오기
	   public static String thumbnail( String boardContent ) {

		   // img 태그 src 추출 정규표현식
		   Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); 

		   String thumbnail = null; // src 속성값을 저장할 임시 참조 변수

		   // SummerNote에 작성된 내용 중 img태그의 src속성의 값을 검사하여 매칭되는 값을 Matcher객체에 저장함.
		   Matcher matcher = pattern.matcher(boardContent);

		   // matcher.find() : Matcher 객체에 저장된 값(검사를 통해 매칭된 src 속성 값)에 반복 접근하여 값이 있을 경우 true
		   // 첫번째 사진이 썸네일이라서 while이 아닌 if문 사용!
		   if(matcher.find()){

			   thumbnail =  matcher.group(1); // 매칭된 src 속성값을  Matcher 객체에서 꺼내서 src에 저장

		   }

		   return thumbnail;

	   }
	   
	
}
