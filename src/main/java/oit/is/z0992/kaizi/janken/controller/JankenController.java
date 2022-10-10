package oit.is.z0992.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
public class JankenController {

  /**
   * work02part1というGETリクエストがあったら work02part1()を呼び出し，work02part1.htmlを返す
*/   
  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  /**
   * POSTを受け付ける場合は@PostMappingを利用する /sample25へのPOSTを受け付けて，FormParamで指定された変数(input
   * name)をsample25()メソッドの引数として受け取ることができる
   *
   * @param username
   * @return
   */
  @PostMapping("/janken")
  public String janken(@RequestParam String username, ModelMap model) {

    model.addAttribute("username", username);
    return "janken.html";
  }

}
