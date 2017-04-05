/**
 * This class help to manipulate dates
 */
class DateService {

  /**
   * Format a moment
   * @param {Object} jsDate to format
   * @return {string} the day formatted
   */
  formatDay(jsDate) {
    return moment(jsDate).locale('fr').format('dddd d MMMM');
  }

  /**
   * Format an hour
   * @param {Object} jsDate to format
   * @return {string} hour
   */
  hour(jsDate) {
    return moment(jsDate).locale('fr').format('hh:mm');
  }
}

export let dateService = new DateService();
