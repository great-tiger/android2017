package com.example.liqingliu.parsejson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnCreate;
    Button btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreate= findViewById(R.id.btnCreate);
        btnRead = findViewById(R.id.btnRead);
        btnCreate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreate:
                create();
                break;
            case R.id.btnRead:
                read();
                break;
        }
    }

    private void read() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getAssets().open("test.xml"));
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("lan");
            for(int i = 0; i < nodeList.getLength(); i++){
                Element lan = (Element)nodeList.item(i);
                String id =  lan.getElementsByTagName("id").item(0).getTextContent();
                String name = lan.getElementsByTagName("name").item(0).getTextContent();
                String tool = lan.getElementsByTagName("tool").item(0).getTextContent();
                System.out.println("--->>> id: "+ id + ", name: "+name + ", tool: "+tool);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void create() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element languages = document.createElement("languages");
            languages.setAttribute("cat","it");

            Element lan,id,name,tool;

            lan = document.createElement("lan");
            id = document.createElement("id");
            name = document.createElement("name");
            tool = document.createElement("tool");
            id.setTextContent("1");
            name.setTextContent("C#");
            tool.setTextContent("VS");
            lan.appendChild(id);
            lan.appendChild(name);
            lan.appendChild(tool);
            languages.appendChild(lan);

            lan = document.createElement("lan");
            id = document.createElement("id");
            name = document.createElement("name");
            tool = document.createElement("tool");
            id.setTextContent("2");
            name.setTextContent("Swift");
            tool.setTextContent("Xcode");
            lan.appendChild(id);
            lan.appendChild(name);
            lan.appendChild(tool);
            languages.appendChild(lan);

            lan = document.createElement("lan");
            id = document.createElement("id");
            name = document.createElement("name");
            tool = document.createElement("tool");
            id.setTextContent("3");
            name.setTextContent("Java");
            tool.setTextContent("Eclipse");
            lan.appendChild(id);
            lan.appendChild(name);
            lan.appendChild(tool);
            languages.appendChild(lan);

            document.appendChild(languages);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document),new StreamResult(stringWriter));
            System.out.println(stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
