package springproject.ziprest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springproject.ziprest.dto.ZipCodeDto;
import springproject.ziprest.service.ZipCodeService;

@Controller
public class ZipCodeController {

	@Autowired
	private ZipCodeService zipCodeService;

	/**
	 * 郵便番号入力フォーム
	 *
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String zipcodeForm(Model model) {
		return "zipcode";
	}

	/**
	 * 郵便番号情報表示
	 *
	 * @param model
	 * @param zipcode
	 * @return "zipcode-confirm"
	 */
	@RequestMapping(value = "/zipcode/confirm", method = RequestMethod.POST)
	public String zipCodeConfirm(Model model, @RequestParam("zipcode") String zipcode) {

		// 必須チェック
		// nullまたは空文字の場合、入力フォームにエラーメッセージを表示
		if (StringUtils.isEmpty(zipcode)) {
			model.addAttribute("errorMessage", "郵便番号を入力してください。");
			return zipcodeForm(model);
		}

		// 郵便番号検索APIサービス呼び出し
		ZipCodeDto zipCodeDto = zipCodeService.execute(zipcode);
		// thymeleafでリストを展開して表示する
		model.addAttribute("zipcodeList", zipCodeDto.getResults());

		return "zipcode-confirm";
	}
}
