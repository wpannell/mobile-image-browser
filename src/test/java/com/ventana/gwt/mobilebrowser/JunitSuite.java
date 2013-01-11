package com.ventana.gwt.mobilebrowser;

import com.ventana.gwt.mobilebrowser.client.ANewlyCreatedMobileBrowserShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedDirectoryActivityOnAddShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedDirectoryActivityOnBrowseShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedDirectoryActivityOnDeleteShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedDirectoryActivityOnEditShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedDirectoryActivityShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedExplorerActivityShould;
import com.ventana.gwt.mobilebrowser.client.activities.ANewlyCreatedHostPageActivityShould;
import com.ventana.gwt.mobilebrowser.client.activities.AnImageActivityShould;
import com.ventana.gwt.mobilebrowser.client.events.ABooleanNotifierShould;
import com.ventana.gwt.mobilebrowser.client.events.APlaceNotifierShould;
import com.ventana.gwt.mobilebrowser.client.factories.ANewlyCreatedActivityFactoryShould;
import com.ventana.gwt.mobilebrowser.client.history.ANewlyCreatedExplorerActivityMapperShould;
import com.ventana.gwt.mobilebrowser.client.places.ADirectoryPlaceShould;
import com.ventana.gwt.mobilebrowser.client.places.AFolderPlaceShould;
import com.ventana.gwt.mobilebrowser.client.places.ARootFolderPlaceShould;
import com.ventana.gwt.mobilebrowser.client.places.AThumbnailPlaceShould;
import com.ventana.gwt.mobilebrowser.client.places.AnExplorerPlaceShould;
import com.ventana.gwt.mobilebrowser.client.places.AnImagePlaceShould;
import com.ventana.gwt.mobilebrowser.client.presenters.ADeletePresenterShould;
import com.ventana.gwt.mobilebrowser.client.presenters.ANewlyCreatedEditPresenterNotValidatingOverHTTPShould;
import com.ventana.gwt.mobilebrowser.client.presenters.ANewlyCreatedEditPresenterSavingShould;
import com.ventana.gwt.mobilebrowser.client.presenters.ANewlyCreatedEditPresenterShould;
import com.ventana.gwt.mobilebrowser.client.presenters.ANewlyCreatedEditPresenterValidatingOverHTTPShould;
import com.ventana.gwt.mobilebrowser.client.presenters.AnImageInformationPresenterShould;
import com.ventana.gwt.mobilebrowser.client.presenters.AnImageInformationPresenterWhichCantGoPreviousOrNextShould;
import com.ventana.gwt.mobilebrowser.client.ui.AHostPageUiShould;
import com.ventana.gwt.mobilebrowser.client.ui.AnExplorerUiShould;
import com.ventana.gwt.mobilebrowser.client.ui.AnImageViewerUiShould;
import com.ventana.gwt.mobilebrowser.client.url.ABase64Should;
import com.ventana.gwt.mobilebrowser.client.url.AHttpProtocolParserShould;
import com.ventana.gwt.mobilebrowser.client.url.ANewlyCreatedFolderIndexParserShould;
import com.ventana.gwt.mobilebrowser.client.url.ANewlyCreatedUrlParserShould;
import com.ventana.gwt.mobilebrowser.client.url.ANewlyCreatedUrlShould;
import com.ventana.gwt.mobilebrowser.client.widgets.APopUpConfirmationWidgetShould;
import com.ventana.gwt.mobilebrowser.client.widgets.APopUpDeleteWidgetShould;
import com.ventana.gwt.mobilebrowser.client.widgets.APopUpEditWidgetShould;
import com.ventana.gwt.mobilebrowser.client.widgets.APopUpImageInformationWidgetShould;
import com.ventana.gwt.mobilebrowser.client.widgets.AToolbarWidgetShould;
import com.ventana.gwt.mobilebrowser.server.ADefaultClientSideServiceShould;
import com.ventana.gwt.mobilebrowser.server.responses.AGetExplorerValidationServiceResponseShould;
import com.ventana.gwt.mobilebrowser.server.responses.AGetImageInformationServiceResponseShould;
import com.ventana.gwt.mobilebrowser.server.responses.ANewlyCreatedGetExplorerServiceResponseShould;
import com.ventana.gwt.mobilebrowser.server.responses.ANewlyCreatedGetNavigatorSiblingsServiceResponseShould;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  //UI
  APopUpEditWidgetShould.class,
  APopUpConfirmationWidgetShould.class,
  APopUpDeleteWidgetShould.class,
  APopUpImageInformationWidgetShould.class,
  AToolbarWidgetShould.class,
  AHostPageUiShould.class, // ...these next 3 classes must be...
  AnExplorerUiShould.class, // ...last for some reason...
  AnImageViewerUiShould.class,

  //the rest (alpha order)
  ABase64Should.class,
  ABooleanNotifierShould.class,
  ADefaultClientSideServiceShould.class,
  ADeletePresenterShould.class,
  ADirectoryPlaceShould.class,
  AGetImageInformationServiceResponseShould.class,
  AHttpProtocolParserShould.class,
  AnExplorerPlaceShould.class,
  AnImageActivityShould.class,
  AnImageInformationPresenterShould.class,
  AnImageInformationPresenterWhichCantGoPreviousOrNextShould.class,
  AnImagePlaceShould.class,
  AFolderPlaceShould.class,
  AGetExplorerValidationServiceResponseShould.class,
  ANewlyCreatedActivityFactoryShould.class,
  ANewlyCreatedDirectoryActivityShould.class,
  ANewlyCreatedDirectoryActivityOnAddShould.class,
  ANewlyCreatedDirectoryActivityOnBrowseShould.class,
  ANewlyCreatedDirectoryActivityOnDeleteShould.class,
  ANewlyCreatedDirectoryActivityOnEditShould.class,
  ANewlyCreatedEditPresenterShould.class,
  ANewlyCreatedEditPresenterNotValidatingOverHTTPShould.class,
  ANewlyCreatedEditPresenterSavingShould.class,
  ANewlyCreatedEditPresenterValidatingOverHTTPShould.class,
  ANewlyCreatedExplorerActivityMapperShould.class,
  ANewlyCreatedExplorerActivityShould.class,
  ANewlyCreatedFolderIndexParserShould.class,
  ANewlyCreatedGetExplorerServiceResponseShould.class,
  ANewlyCreatedGetNavigatorSiblingsServiceResponseShould.class,
  ANewlyCreatedHostPageActivityShould.class,
  ANewlyCreatedMobileBrowserShould.class,
  ANewlyCreatedUrlParserShould.class,
  ANewlyCreatedUrlShould.class,
  APlaceNotifierShould.class,
  AThumbnailPlaceShould.class,
  ARootFolderPlaceShould.class,
})
public class JunitSuite {}
