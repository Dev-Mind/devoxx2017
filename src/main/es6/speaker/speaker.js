export class SpeakerCtrl {
  /**
   * Call server to load speakers
   */
  init() {
    fetch('api/speakers').then(response => {
      response.json().then(json => {
        this.data = json;
        window.document.getElementById('dmSpeakers').innerHTML = this._getHtmlContent();
      });
    });
  }

  /**
   * Display the list of the sessions
   * @private
   * @return {string} html to display
   */
  _getHtmlContent() {
    let lines = '<div class="dm_list">';

    if (this.data) {
      this.data.forEach(speaker => {
        // We want to display the speakers
        lines += `<div class="dm_list__item" onClick="location.href='/#speaker/${speaker.idMember}'">
          <h2 class="dm_speaker__title"><a href="#speaker/${speaker.idMember}">${speaker.firstname} ${speaker.lastname}</a></h2>
          <p>${speaker.shortDescription}</p>
      </div>`;
      });
    }
    lines += '</div>';
    return lines;
  }
}
