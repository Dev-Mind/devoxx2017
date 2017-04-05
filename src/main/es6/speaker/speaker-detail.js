/**
 * This class is used to display the speakers
 */
export class SpeakerDetailCtrl {

  /**
   * Call server to load a speaker
   * @param {string} idSpeaker speaker id
   */
  init(idSpeaker) {
    fetch('api/speakers/' + idSpeaker).then(response => {
      response.json().then(json => {
        this.speaker = json;
        window.document.getElementById('dmSpeakerTitle').innerHTML = this._getHtmlTitle();
        window.document.getElementById('dmSpeaker').innerHTML = this._getHtmlContent();
      });
    }, () => window.document.getElementById('dmSpeaker').innerHTML = 'Error on speaker fetch');
  }

  /**
   * Display a speaker
   * @private
   * @return {string} title to display
   */
  _getHtmlTitle() {
    if (!this.speaker) {
      return 'Speaker inconnu';
    }
    return `${this.speaker.firstname} ${this.speaker.lastname}`;
  }
  /**
   * Display a speaker
   * @private
   * @return {string} html to display
   */
  _getHtmlContent() {
    let lines = '';
    if (this.speaker) {
      lines += `<p><span class="dm_speaker__company">${this.speaker.company ? this.speaker.company : ''}</span></p>
          <p class="dm_speaker__summary">${this.speaker.shortDescription}</p>
          <p class="dm_speaker__description">${this.speaker.longDescription ? this.speaker.longDescription : ''}</p>`;
    }
    return lines;
  }
}
