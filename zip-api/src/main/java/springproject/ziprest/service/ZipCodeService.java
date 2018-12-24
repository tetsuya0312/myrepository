package springproject.ziprest.service;

import springproject.ziprest.dto.ZipCodeDto;

/**
 * 郵便番号検索サービスのインタフェース。
 * <p>
 * 郵便番号検索サービスAPIを呼び出す。
 *
 * @author tetsuya.okuyama
 *
 */
public interface ZipCodeService {

	/**
	 * 郵便番号検索
	 * <p>
	 * 郵便番号検索サービスAPIを呼び出し、指定の郵便番号に紐づく住所情報を取得する。
	 *
	 * @param zipcode
	 *            郵便番号
	 * @return 住所情報
	 */
	public ZipCodeDto execute(String zipcode);
}
