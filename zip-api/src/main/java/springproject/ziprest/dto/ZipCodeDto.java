package springproject.ziprest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 住所情報DTO
 *
 * @author tetsuya.okuyama
 */
@Data
public class ZipCodeDto {
	/** ステータス */
	private int status;
	/** メッセージ */
	private String meesage;
	/** 郵便番号情報リスト */
	private List<ZipCodeDataDto> results = new ArrayList<>();
}
