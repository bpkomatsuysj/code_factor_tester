package jp.co.code.factor.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 月初日であるかを管理するEnumクラス.
 */
@AllArgsConstructor
@Getter
public enum FirstDayOfTheMonthEnum {

  /** 0:出力する. */
  YES(0, "月初日である"),
  /** 1:出力しない. */
  NO(1, "月初日ではない");

  private int code;
  private String label;

  /**
   * インスタンス生成.
   * 
   * @param code コード
   * @return インスタンス
   */
  public static FirstDayOfTheMonthEnum getInstance(int code) {
    for (FirstDayOfTheMonthEnum o : FirstDayOfTheMonthEnum.values()) {
      if (o.getCode() == code) {
        return o;
      }
    }
    return null;
  }
}
