import {dateService} from '../date/date.js';

/**
 * This class is used to display the sessions
 */
export class SessionDetailCtrl {

  /**
   * Call server to load a session
   * @param {string} idSession session id
   */
  init(idSession) {
    fetch('api/sessions/' + idSession).then(response => {
      response.json().then(json => {
        this.session = json;
        window.document.getElementById('dmSessionTitle').innerHTML = this._getHtmlTitle();
        window.document.getElementById('dmSession').innerHTML = this._getHtmlContent();
      });
    }, () => window.document.getElementById('dmSession').innerHTML = 'Error on session fetch');
  }

  /**
   * Display a session
   * @private
   * @return {string} title to display
   */
  _getHtmlTitle() {
    if (!this.session) {
      return 'Session inconnue';
    }
    return this.session.title;
  }

  /**
   * Display a session
   * @private
   * @return {string} html to display
   */
  _getHtmlContent() {
    let lines = '';
    let speakers = '';

    if (this.session) {
      this.session.speakers.forEach(speaker => {
        speakers += `<li>${speaker.firstname} ${speaker.lastname} </li>`;
      });

      lines += `<p>${dateService.formatDay(this.session.start)} de ${dateService.hour(this.session.start)} à ${dateService.hour(this.session.end)}</p>
          <p><span class="dm_session__room">${this.session.room}</span></p>
          <p class="dm_session__summary">${this.session.summary}</p>
          <p class="dm_session__description">${this.session.description}</p>
          <h2>Speakers</h2>
          <ul>${speakers}</ul>`;
    }
    return lines;
  }
}
