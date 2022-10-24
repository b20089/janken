package oit.is.z0992.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT id,name, from users where id = #{id}")
  User selectById(int id);

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param matches
   */
  @Insert("INSERT INTO users (name) VALUES #{name};")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllByMatches();

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  /*
   * @Select("SELECT chamber.userName,chamber.chamberName,userinfo.age,userinfo.height, from chamber JOIN userinfo ON chamber.userName=userinfo.userName;"
   * )
   * ArrayList<ChamberUser> selectAllChamberUser();
   *
   * @Insert("INSERT INTO userinfo (userName,age,height) VALUES (#{userName},#{age},#{height});"
   * )
   * void insertUserInfo(UserInfo userinfo);
   *
   *
   */
}
