import { browser, by, element } from "protractor";

export class AppPage {
  navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl) as Promise<unknown>;
  }

  navigateToProducts(): Promise<unknown> {
    return browser.get(browser.baseUrl + "/products") as Promise<unknown>;
  }

  getTitleText(): Promise<string> {
    return element(by.css(".navbar-brand")).getText() as Promise<string>;
  }
  //app-root .content span
  //  protractor protractor.conf.js
}
