import {dateService} from '../../date/date.js';

/**
 * This class is just here to simulate the behavior of a real app with a lot of
 * JS ressources
 */
export class Fake4_4_Ctrl {

  /**
   * Call server to load sessions
   */
  init() {
    fetch('api/sessions').then(response => {
      response.json().then(json => {
        this.data = json;
        window.document.getElementById('dmSessions').innerHTML = this._getHtmlContent();
      });
    }, () => window.document.getElementById('dmSessions').innerHTML = 'Error on session fetch');
  }

  /**
   * Returns a string with lastname and firstname of the speakers
   * @param {string} session - current session
   * @private
   * @return {string} concatenated infos
   */
  _mapSpeaker(session) {
    // We want to display the speakers
    let speakers = '';
    session.speakers.forEach(speaker => {
      speakers += `${speaker.firstname} ${speaker.lastname} `;
    });
    return speakers;
  }

  /**
   * Computes the days of the conference
   * @private
   * @return {Array} day1 and day2
   */
  _getConfDays() {
    let day1 = dateService.formatDay(moment(this.data[0].start));
    let day2 = dateService.formatDay(moment(this.data[this.data.length - 1].start));
    return [day1, day2];
  }

  /**
   * Displays the list of the sessions
   * @private
   * @return {string} html to display
   */
  _getHtmlContent() {
    if (!this.data) {
      return '';
    }

    let [day1, day2] = this._getConfDays();
    let lines = `<ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#day1" data-toggle="tab">${day1}</a>
                    </li>
                    <li role="presentation">
                        <a href="#day2" data-toggle="tab">${day2}</a>
                    </li>
                 </ul>
                 <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active dm_list" id="day1">
                 `;

    let day = day1;
    this.data.forEach(session => {
      // We want to display the speakers
      if (day !== dateService.formatDay(session.start)) {
        day = dateService.formatDay(session.start);
        lines += `</div><div role="tabpanel" class="tab-pane active dm_list" id="day2">`;
      }
      lines += `<div class="dm_list__item" onClick="location.href='/#session/${session.idSession}'">
            <h2 class="dm_session__title"><a href="#session/${session.idSession}">${session.title}</a></h2>
            <p class="dm_session__time">de ${dateService.hour(session.start)} à ${dateService.hour(session.end)}
                &nbsp;<span class="dm_session__room">${session.room}</span></p>
            <p>${session.summary}</p>
            <p class="dm_session__speaker">${this._mapSpeaker(session)}</p>
          </div>`;
    });
    lines += '</div>';
    return lines;
  }
}
