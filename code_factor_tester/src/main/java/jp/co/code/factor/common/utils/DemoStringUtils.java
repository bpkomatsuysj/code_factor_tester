package jp.co.code.factor.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import jp.co.code.factor.common.constans.CommonConsts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 文字列操作ユーティリティクラス.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoStringUtils {

  /**
   * Forward URL を返却します.
   * 
   * @param url url
   * @return Forward URL
   */
  public static final String buildForwardUrl(String url) {
    return CommonConsts.FORWARD + CommonConsts.COLON + url;
  }

  /**
   * Redirect URL を返却します.
   * 
   * @param url url
   * @return Redirect URL
   */
  public static final String buildRedirectUrl(String url) {
    return CommonConsts.REDIRECT + CommonConsts.COLON + url;
  }

  /**
   * 末尾のゼロをトリムします.
   * 
   * @param number トリムする数字
   * @return トリム後の文字列
   */
  public static String trimTrailingZero(int number) {
    return String.valueOf(number).replaceAll("0+$", CommonConsts.EMPTY);
  }

  /**
   * 数値の左側を0埋めします.
   * 
   * @param number 0埋めされる整数
   * @param howManyChar 0埋め後に期待する文字数
   * @return 0埋め後の文字列
   */
  public static final String leftZeroPadding(int number, int howManyChar) {
    return String.format("%0" + howManyChar + "d", number);
  }

  /**
   * SQLのエスケープ処理を行います<br< Oracle11.2から、全角はLIKEの中のエスケープが不要になった.
   * 
   * @param src 対象文字列
   * @return エスケープ後文字列
   */
  public static final String escapeSqlParam(String src) {
    return src.replace("$", "$$").replace("%", "$%").replace("_", "$_");
  }

  /**
   * オブジェクトのリストについて、オブジェクトの項目値を文字列にして返却します.
   * 
   * @param <T> オブジェクト
   * @param newList オブジェクトリスト
   * @return 文字列リスト
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  public static final <T> List<List<String>> toStringList(List<? super T> newList)
      throws IllegalArgumentException, IllegalAccessException {
    List<List<String>> result = new ArrayList<>();

    for (Object o : newList) {

      List<String> list = new ArrayList<>();

      Field[] fields = o.getClass().getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        list.add(String.valueOf(field.get(o)));
      }

      result.add(list);
    }

    return result;
  }
}
