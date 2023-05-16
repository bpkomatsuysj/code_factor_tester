package jp.co.code.factor.common.utils;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.thymeleaf.util.StringUtils;
import jp.co.code.factor.common.constans.CommonConsts;
import jp.co.code.factor.common.enums.FirstDayOfTheMonthEnum;
import jp.co.code.factor.common.enums.LastDayOfTheMonthEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 日付操作ユーティリティクラス.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoDateUtils {

  /** format string yyyy/MM. */
  public static final String FORMAT_YYYYMM_SLASH = "yyyy/MM";

  /** format string yyyyMMdd. */
  public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

  /** format string yyyy/MM/dd. */
  public static final String FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";

  /** format string yyyy/[]M/[]d (月日の桁数は1・2桁、どちらも許容). */
  public static final String FORMAT_YYYYMMDD_SLASH_VAGUE_DIGIT = "yyyy/[]M/[]d";


  /**
   * 年月日文字列を日付に変換できるか判定します.<br>
   * ※形式: yyyy/MM/dd or yyyy-MM-dd
   * 
   * @param str チェック対象の文字列
   * @return 存在する日付の場合true
   */
  public static final boolean isConvertYmdStr(String str) {
    str = str.replace('-', '/');
    DateFormat format = DateFormat.getDateInstance();
    format.setLenient(false); // 日付/時刻解析を厳密に実施

    try {
      format.parse(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * リストに日付が存在するか判定します.
   * 
   * @param list 検索されるリスト
   * @param date リストに存在するか判定される日付
   * @return 存在する:true
   */
  public static final boolean containsDateInList(List<LocalDate> list, LocalDate date) {

    Comparator<LocalDate> comparator = new Comparator<LocalDate>() {
      @Override
      public int compare(LocalDate d1, LocalDate d2) {
        return d1.compareTo(d2);
      }
    };

    if (Collections.binarySearch(list, date, comparator) >= 0) {
      return true;
    }
    return false;
  }

  /**
   * 文字列型の年月(区切り文字無し)をLocalDate型に変換します.<br>
   * 日にちの指定がない場合は初日とします.
   * 
   * @param str 年月(スラッシュ区切り)
   * @param day day 日
   * @return LocalDate
   */
  public static final LocalDate convertYearMonthToLocalDate(String str, Integer day) {

    if (StringUtils.isEmpty(str) || str.length() != 6) {
      return null;
    }

    if (day == null || day.equals(Integer.valueOf(0))) {
      day = 1;
    }

    int year = Integer.valueOf(str.substring(0, 4));
    int month = Integer.valueOf(str.substring(4, 6));

    return LocalDate.of(year, month, day);
  }

  /**
   * 文字列型のスラッシュ区切りの年月をLocalDate型に変換します.<br>
   * 日にちの指定がない場合は初日とします.
   * 
   * @param str 年月(スラッシュ区切り)
   * @param day day 日
   * @return LocalDate
   */
  public static final LocalDate convertSlashDelimiterYearMonthToLocalDate(String str, Integer day) {

    if (StringUtils.isEmpty(str)) {
      return null;
    }

    if (day == null || day.equals(Integer.valueOf(0))) {
      day = 1;
    }

    int year = Integer.valueOf(str.split(CommonConsts.SLASH)[0]);
    int month = Integer.valueOf(str.split(CommonConsts.SLASH)[1]);

    return LocalDate.of(year, month, day);
  }

  /**
   * String型の日付を、指定の形式に変換します.
   * 
   * @param str 日付
   * @param beforeFormat 変換前の形式
   * @param afterFormat 変換後の形式
   * @return 変換後の日付
   */
  public static final String formatStrDate(String str, String beforeFormat, String afterFormat) {
    LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern(beforeFormat));
    return date.format(DateTimeFormatter.ofPattern(afterFormat));
  }

  /**
   * String型の日付をLocalDate型に変換します.
   * 
   * @param date 変換日付
   * @param format 形式
   * @return LocalDate
   */
  public static final LocalDate convertStrToLocalDate(String date, String format) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
  }

  /**
   * String型の日付をLocalDateTime型に変換します.
   * 
   * @param date 変換日付
   * @param format 形式
   * @return LocalDate
   */
  public static final LocalDateTime convertStrToLocalDateTime(String date, String format) {
    return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
  }

  /**
   * String型の年月日をLocalDateTime型に変換します.
   * 
   * @param date 変換年月日
   * @param format 形式
   * @return LocalDate
   */
  public static final LocalDateTime convertYmdStrToLocalDateTime(String date, String format) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern(format)).atStartOfDay();
  }

  /**
   * Date型の日付をLocalDate型に変換します.
   * 
   * @param date 変換日付
   * @return LocalDate
   */
  public static final LocalDate convertDateToLocalDate(final Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  /**
   * Date型の日付をLocalDateTime型に変換します.
   * 
   * @param date 変換日付
   * @return LocalDateTime
   */
  public static final LocalDateTime convertDateToLocalDateTime(final Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /**
   * LocalDateを文字列に変換します.
   * 
   * @param date LocalDate
   * @param format 日付形式
   * @return 日付文字列
   */
  public static final String convertLocalDateToStr(LocalDate date, String format) {
    return date.format(DateTimeFormatter.ofPattern(format));
  }

  /**
   * LocalDate型の日付をDate型に変換します.
   * 
   * @param localDate 変換日付
   * @return Date
   */
  public static final Date convertLocalDateToDate(final LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  /**
   * LocalDate型の日付をLocalDateTime型に変換します.<br>
   * ※時分秒は、すべて 0 で初期化します.
   * 
   * @param localDate 変換日付
   * @return LocalDateTime
   */
  public static final LocalDateTime convertLocalDateToLocalDateTime(final LocalDate localDate) {
    return LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
  }

  /**
   * LocalDateTimeを文字列に変換します.
   * 
   * @param date LocalDateTime
   * @param format 日付形式
   * @return 日付文字列
   */
  public static final String convertLocalDateTimeToStr(LocalDateTime date, String format) {
    return date.format(DateTimeFormatter.ofPattern(format));
  }

  /**
   * LocalDate型の日付をDate型に変換します.
   * 
   * @param localDate 変換日付
   * @return Date
   */
  public static final Date convertLocalDateTimeToDate(final LocalDateTime localDateTime) {
    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    return Date.from(zonedDateTime.toInstant());
  }

  /**
   * 指定日付が月初日であるか判定します.
   * 
   * @param date 指定日付
   * @return enum
   */
  public static final FirstDayOfTheMonthEnum isFirstDayOfTheMonth(LocalDate date) {

    // 月初日 取得
    LocalDate firstDayOfTheMonth = getFirstLocalDateOfMonth(date);

    if (date.isEqual(firstDayOfTheMonth)) {
      return FirstDayOfTheMonthEnum.YES;
    }

    return FirstDayOfTheMonthEnum.NO;
  }

  /**
   * 指定日付が月末日であるか判定します.
   * 
   * @param date 指定日付
   * @return enum
   */
  public static final LastDayOfTheMonthEnum isLastDayOfTheMonth(LocalDate date) {

    // 月末日 取得
    LocalDate lastDayOfTheMonth = getLastLocalDateOfMonth(date);

    if (date.isEqual(lastDayOfTheMonth)) {
      return LastDayOfTheMonthEnum.YES;
    }

    return LastDayOfTheMonthEnum.NO;
  }

  /**
   * 指定日付の月初を返却します.
   * 
   * @param date 指定日付
   * @return LocalDate
   */
  public static final LocalDate getFirstLocalDateOfMonth(LocalDate date) {
    return LocalDate.of(date.getYear(), date.getMonthValue(), 1);
  }

  /**
   * 指定日付の月末を返却します.
   * 
   * @param date 指定日付
   * @return LocalDate
   */
  public static final LocalDate getLastLocalDateOfMonth(LocalDate date) {
    return LocalDate.of(date.getYear(), date.getMonthValue(), date.lengthOfMonth());
  }

  /**
   * システム日付を指定形式の文字列で返却します.
   * 
   * @param format 日付形式
   * @return システム日付文字列
   */
  public static final String getSysDateStr(String format) {
    return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
  }

  /**
   * システム日付の月初をLocalDateで返却します.
   * 
   * @return LocalDate
   */
  public static final LocalDate getSysFirstLocalDateOfMonth() {
    return getFirstLocalDateOfMonth(LocalDate.now());
  }

  /**
   * システム日付の月末をLocalDateで返却します.
   * 
   * @return LocalDate
   */
  public static final LocalDate getSysLastLocalDateOfMonth() {
    return getLastLocalDateOfMonth(LocalDate.now());
  }

  /**
   * システム日付の月初を文字列で返却します.
   * 
   * @return string
   */
  public static final String getSysFirstDateStrOfMonth(String format) {
    return convertLocalDateToStr(getFirstLocalDateOfMonth(LocalDate.now()), format);
  }

  /**
   * システム日付の月末を文字列で返却します.
   * 
   * @return string
   */
  public static final String getSysLastDateStrOfMonth(String format) {
    return convertLocalDateToStr(getLastLocalDateOfMonth(LocalDate.now()), format);
  }

  /**
   * 指定日付の年月を返却します.
   * 
   * @param date 日付
   * @param delimiter 年月の区切り文字(null不可。空白可)
   * @return 年月文字列
   */
  public static final String getYearMonthStr(LocalDate date, String delimiter) {
    String year = String.valueOf(date.getYear());
    String month = DemoStringUtils.leftZeroPadding(date.getMonthValue(), 2);
    return year + delimiter + month;
  }

}
