package oit.is.z0992.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import大事
import oit.is.z0992.kaizi.janken.model.Entry;
import oit.is.z0992.kaizi.janken.model.Match;
import oit.is.z0992.kaizi.janken.model.MatchMapper;
import oit.is.z0992.kaizi.janken.model.Matchinfo;
import oit.is.z0992.kaizi.janken.model.MatchinfoMapper;
import oit.is.z0992.kaizi.janken.model.User;
import oit.is.z0992.kaizi.janken.model.UserMapper;

/**
 * sample21Controller
 *
 * クラスの前に@Controllerをつけていると，HTTPリクエスト（GET/POSTなど）があったときに，このクラスが呼び出される
 */
@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchinfoMapper matchinfoMapper;
  /**
   * work02part1というGETリクエストがあったら work02part1()を呼び出し，work02part1.htmlを返す
   */
  /*
   * @GetMapping("/janken")
   * public String janken() {
   * return "janken.html";
   * }
   */

  /**
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22()の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param param1
   * @param model
   * @return
   */
  @GetMapping("/janken1/{param1}")
  public String janken(@PathVariable String param1, ModelMap model) {
    int i = Integer.parseInt(param1);// param1が文字列なので，parseIntでint型の数値に変換する
    // グー：１，チョキ：２，パー：３
    String te = null, result = null;
    if (i == 1) {
      te = "Gu";
      result = "draw...";
    } else if (i == 2) {
      te = "Choki";
      result = "You Lose...";
    } else if (i == 3) {
      te = "Pa";
      result = "You Win!";
    }
    // ModelMap型変数のmodelにtasuResult1という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    model.addAttribute("te", te);
    model.addAttribute("result", result);

    ArrayList<Match> matches = matchMapper.selectAllByMatches();
    model.addAttribute("matches", matches);

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
  public String jankenpost(@RequestParam String username, ModelMap model) {

    model.addAttribute("username", username);

    return "janken.html";
  }

  /**
   *
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin  ログインユーザ情報が保持されるオブジェクト
   * @return
   */
  @GetMapping("/janken")
  public String entryJanken(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("username", loginUser);

    ArrayList<User> users = userMapper.selectAllByUsers();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllByMatches();
    model.addAttribute("matches", matches);

    ArrayList<Matchinfo> matchinfo = matchinfoMapper.selectByisActive();
    model.addAttribute("matchinfo", matchinfo);
    // 画面に渡す処理達

    return "janken.html";
  }

  /**
   * PQueryParameterにidを与えることで対戦相手を特定するので，UserMapperにidを利用してUserオブジェクトを取得するメソッドを追加する
   *
   * @param id
   * @param model Thymeleafにわたすデータを保持するオブジェクト
   * @param prin
   * @return
   */
  @GetMapping("/match")
  public String match(@RequestParam Integer id, ModelMap model, Principal prin) {
    User aite = userMapper.selectById(id);

    model.addAttribute("aite", aite);
    model.addAttribute("username", prin.getName());

    return "match.html";
  }

  @GetMapping("/fight") // @GetMapper(“/fight”)がわかりません
  public String matchJanken(@RequestParam Integer param1, ModelMap model, Principal prin) {
    // グー：１，チョキ：２，パー：３
    String te = null, result = null;
    if (param1 == 1) {
      te = "Gu";
      result = "draw...";
    } else if (param1 == 2) {
      te = "Choki";
      result = "You Lose...";
    } else if (param1 == 3) {
      te = "Pa";
      result = "You Win!";
    }
    // 使いまわしのプログラムから抜き出してinsertしたい
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    model.addAttribute("te", te);
    model.addAttribute("result", result);
    User jibun = userMapper.selectByName(prin.getName());
    Match match = new Match();
    match.setUser1(jibun.getId());
    match.setUser2(1);// CPU
    match.setUser1Hand(te);
    match.setUser2Hand("Gu");// CPUの手
    matchMapper.insertMatches(match);

    ArrayList<Match> matches = matchMapper.selectAllByMatches();
    model.addAttribute("matches", matches);

    return "match.html";

  }

  /**
   *
   * @param param1
   * @param id
   * @param model  Thymeleafにわたすデータを保持するオブジェクト
   * @param prin
   * @return
   */
  @GetMapping("/wait")
  public String waitJanken(@RequestParam Integer param1, @RequestParam Integer id, ModelMap model, Principal prin) {
    // グー：１，チョキ：２，パー：３
    String te = null;
    if (param1 == 1) {
      te = "Gu";
    } else if (param1 == 2) {
      te = "Choki";
    } else if (param1 == 3) {
      te = "Pa";
    }

    User jibun = userMapper.selectByName(prin.getName());
    Matchinfo info = new Matchinfo();
    info.setUser1(jibun.getId());
    info.setUser2(id);// CPU
    info.setUser1Hand(te);
    info.setIsActive(true);
    // match.setUser2Hand("Gu");// matchinfoにはいらない

    // autowiredの存在を忘れていた．
    matchinfoMapper.insertMatchinfo(info);

    ArrayList<Matchinfo> matchinfo = matchinfoMapper.selectAllByMatchinfo();
    model.addAttribute("matchinfo", matchinfo);
    model.addAttribute("username", prin.getName());

    return "wait.html";

  }
}
