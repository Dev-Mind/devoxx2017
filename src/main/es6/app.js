/**
 * In this class we register the controllers used in our pages
 */
import {HomeCtrl} from './home/home.js';
import {SessionCtrl} from './session/session.js';
import {SessionDetailCtrl} from './session/session-detail.js';
import {SpeakerCtrl} from './speaker/speaker.js';
import {SpeakerDetailCtrl} from './speaker/speaker-detail.js';
import {SponsorCtrl} from './sponsor/sponsor.js';
import {Fake1_1_Ctrl} from './fake/1/fake1.js';
import {Fake1_2_Ctrl} from './fake/1/fake2.js'
import {Fake1_3_Ctrl} from './fake/1/fake3.js'
import {Fake1_4_Ctrl} from './fake/1/fake4.js'
import {Fake1_5_Ctrl} from './fake/1/fake5.js'
import {Fake2_1_Ctrl} from './fake/2/fake1.js';
import {Fake2_2_Ctrl} from './fake/2/fake2.js';
import {Fake2_3_Ctrl} from './fake/2/fake3.js';
import {Fake2_4_Ctrl} from './fake/2/fake4.js';
import {Fake2_5_Ctrl} from './fake/2/fake5.js';
import {Fake3_1_Ctrl} from './fake/3/fake1.js';
import {Fake3_2_Ctrl} from './fake/3/fake2.js';
import {Fake3_3_Ctrl} from './fake/3/fake3.js';
import {Fake3_4_Ctrl} from './fake/3/fake4.js';
import {Fake3_5_Ctrl} from './fake/3/fake5.js';
import {Fake4_1_Ctrl} from './fake/4/fake1.js';
import {Fake4_2_Ctrl} from './fake/4/fake2.js';
import {Fake4_3_Ctrl} from './fake/4/fake3.js';
import {Fake4_4_Ctrl} from './fake/4/fake4.js';
import {Fake4_5_Ctrl} from './fake/4/fake5.js';

/* eslint-env browser */
export class Application {

  constructor() {
    this._initServiceWorker();

    this.components = new Map();
    this.oldtarget = undefined;

    if (!self.fetch) {
      console.error('This app used the fetch API to load data, but your browser don\'t support this feature. Add a pollyfill');
    }
    // Register all the components
    this._registerComponent('home', '()', new HomeCtrl(), 'home/home.html');
    this._registerComponent('session', '(session)', new SessionCtrl(), 'session/session.html');
    this._registerComponent('session-detail', '(session/)(\\w+)', new SessionDetailCtrl(), 'session/session-detail.html');
    this._registerComponent('speaker', '(speaker)', new SpeakerCtrl(), 'speaker/speaker.html');
    this._registerComponent('speaker-detail', '(speaker/)(\\w+)', new SpeakerDetailCtrl(), 'speaker/speaker-detail.html');
    this._registerComponent('sponsor', '(sponsor)', new SponsorCtrl(), 'sponsor/sponsor.html');

    this._initUrl();
    this.collapse = true;
    this.collapseMenu();
  }

  /**
   * This event handler reload the good template when the hash change in location
   */
  locationHashChanged() {
    this._initUrl();
  }

  _initServiceWorker() {
    let isLocalhost = Boolean(window.location.hostname === 'localhost' ||
      window.location.hostname === '[::1]' ||
      window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/)
    );

    if ('serviceWorker' in navigator && (window.location.protocol === 'https:' || isLocalhost)) {
      navigator.serviceWorker.register('service-worker.js').then(function(registration) {
        // Check to see if there's an updated version of service-worker.js with
        if (typeof registration.update === 'function') {
          registration.update();
        }

        // updatefound is fired if service-worker.js changes.
        registration.onupdatefound = function() {
          if (navigator.serviceWorker.controller) {
            var installingWorker = registration.installing;

            installingWorker.onstatechange = function() {
              switch (installingWorker.state) {
                case 'installed':
                  let element = window.document.getElementById('dmMessage');
                  element.innerHTML = 'New content is available; please refresh.';
                  element.style.display = 'block';
                  setTimeout(() => element.style.display = 'none', 2000);
                  break;

                case 'redundant':
                  throw new Error('The installing service worker became redundant.');

                default:
                  // Ignore
              }
            };
          }
        };
      }).catch(function(e) {
        console.error('Error during service worker registration:', e);
      });
    }
  }
  /**
   * If user refresh a screen the component name is read in the URL. The default one is
   * session component
   * @private
   */
  _initUrl() {
    let hash = window.location.hash;
    let target = hash ? hash.substr(1, hash.length) : 'home';
    let go;

    // Mini router
    this.components.forEach((value, key) => {
      // If pattern match we know the target
      if (target.match(value.pattern)) {
        let args = target.split('/');
        args[0] = key;
        go = args;
      }
    });
    this.go(go ? go : 'home');
  }

  /**
   * Register a new controller and a view to be able to load them later.
   * @param {string} name of the component
   * @param {string} pattern to be able to mach with URL
   * @param {Object} ctrl used by the component
   * @param {string} view path
   * @private
   */
  _registerComponent(name, pattern, ctrl, view) {
    this.components.set(name, {
      controller: ctrl,
      pattern: pattern,
      view: view
    });
  }

  /**
   * Load a template in the main page
   * @param {Array} args component name and all the options
   */
  go(args) {
    args = args instanceof Array ? args : [args];
    let [action, ...options] = args;
    this._activeElement(`nav__${this.oldtarget}`, false);
    this.oldtarget = action;
    this._activeElement(`nav__${action}`, true);
    this._displayContent(action, 'dmContent', options);
    this._displayContent('sponsor', 'dmSponsor', options);
    if(this.collapse){
      this.collapseMenu();
    }
  }

  /**
   * Displays a component
   * @param {string} action is the name of the controller
   * @param {string} target is the html id where content will be injected
   * @param {Array} options sent to the controller
   * @private
   */
  _displayContent(action, target, options) {
    let component = this.components.get(action);
    fetch(component.view).then(response => {
      response.text().then(html => document.getElementById(target).innerHTML = html);
    });
    component.controller.init(options);
  }

  /**
   * Active or desactive an element of the menu
   * @param {number} id of the element
   * @param {boolean} active indicator to add or remove the classname 'active'
   * @private
   */
  _activeElement(id, active) {
    if (!id) {
      return;
    }
    let element = document.getElementById(id);
    if (element) {
      if (active) {
        element.classList.add('active');
      } else {
        element.classList.remove('active');
      }
    }
  }

  /**
   * Collapse the menu on mobile
   */
  collapseMenu() {
    this.collapse = !this.collapse;
    document.getElementById('navbar1').style.visibility = this.collapse ? 'visible': 'hidden';
    document.getElementById('navbar1').style.height = this.collapse ? '8em': '0';
  }
}

window.onload = () => {
  window.app = new Application();
  console.log('App is loaded');
  window.addEventListener('hashchange', app.locationHashChanged.bind(app));
}
