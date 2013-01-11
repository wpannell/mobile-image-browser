package com.ventana.gwt.mobilebrowser.client.testhelpers;

public class TestData {

  public static String ISCAN =
      "<iScan " +
          "BuildDate=\"June, 17 2010\" " +
          "BuildVersion=\"3.1.1.1\" " +
          "FocusMode=\"0\" " +
          "FocusQuality=\"1\" " +
          "LabelBoundary=\"1000\" " +
          "Magnification=\"20\" " +
          "ScanMode=\"1\" " +
          "ScanRes=\"0.46500\" " +
          "ShowLabel=\"1\" " +
          "SlideAnnotation=\"\" " +
          "UnitNumber=\"BI09N0242\" " +
          "UserName=\"admin\" " +
          "Z-layers=\"1\" " +
          "Z-spacing=\"1\">" +
          "<AOI0 " +
              "AOIScanned=\"1\" " +
              "Bottom=\"1545\" " +
              "Left=\"295\" " +
              "Right=\"625\" " +
              "Top=\"515\"/>" +
      "</iScan>";

  public static String ROOT_NODE =
      "<ServerContent>" +
        "<Folders>" +
          "<Folder>" +
            "<Name>Jp2Images</Name>" +
          "</Folder>" +
        "</Folders>" +
        "<File/>" +
      "</ServerContent>";

  public static String CHILD_NODE =
      "<ServerContent>" +
        "<Folders/>" +
          "<Files>" +
            "<File>" +
              "<Name>CD138_bone_marrow.bif</Name>" +
              "<Time>12/15/2011 10:45 PM</Time>" +
              "<Size>278392196</Size>" +
            "</File>" +
            "<File>" +
              "<Name>Glioma-HE.bif</Name>" +
              "<Time>12/15/2011 10:45 PM</Time>" +
              "<Size>433536522</Size>" +
            "</File>" +
            "<File>" +
              "<Name>PSOR_CD3.jp2</Name>" +
              "<Time>12/15/2011 09:07 PM</Time>" +
              "<Size>85750354</Size>" +
            "</File>" +
            "<File>" +
              "<Name>PSOR_CD3_1.jp2</Name>" +
              "<Time>12/15/2011 10:45 PM</Time>" +
              "<Size>85750354</Size>" +
            "</File>" +
            "<File>" +
              "<Name>test4.bif</Name>" +
              "<Time>12/15/2011 09:08 PM</Time>" +
              "<Size>53407380</Size>" +
            "</File>" +
            "<File>" +
              "<Name>test4_1.bif</Name>" +
              "<Time>12/15/2011 10:45 PM</Time>" +
              "<Size>53407380</Size>" +
            "</File>" +
          "</Files>" +
        "</ServerContent>";
}
