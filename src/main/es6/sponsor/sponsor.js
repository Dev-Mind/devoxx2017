/**
 * This class is used to display the sessions
 */
export class SponsorCtrl {

  /**
   * Call server to load sessions
   */
  init() {
    fetch('api/sponsors').then(response => {
      response.json().then(json => {
        this.data = json;
        window.document.getElementById('dmSponsors').innerHTML = this._getHtmlContent();
      });
    }, () => window.document.getElementById('dmSponsors').innerHTML = 'Error on session fetch');
  }

  _getWebPUrl(url) {
    return url.substr(0, url.lastIndexOf('.')) + '.webp';
  }

  /**
   * Displays the list of the sponsors
   * @private
   * @return {string} html to display
   */
  _getHtmlContent() {
    if (!this.data) {
      return '';
    }
    let lines = '<div class="dm_sponsors">';
    this.data.forEach(sponsor => {
      lines += `
        <div class="dm_sponsor__container">
          <picture>
            <source srcset="img/${this._getWebPUrl(sponsor.logo)}" type="image/webp" class="dm_sponsor__img img-responsive">
            <source srcset="img/${sponsor.logo}" class="dm_sponsor__img img-responsive">
            <img src="img/${sponsor.logo}" class="dm_sponsor__img img-responsive">
          </picture>
        </div>`;
    });
    lines += '</div>';
    return lines;
  }
}
