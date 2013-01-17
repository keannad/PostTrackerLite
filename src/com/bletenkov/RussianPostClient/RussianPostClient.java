package com.bletenkov.RussianPostClient;

import com.bletenkov.SoapClient.SoapClient;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

public class RussianPostClient extends Object {

    public static String def_post_url = "http://vfc.russianpost.ru:8080/FederalClient/ItemDataService?wsdl";
    public static int max_tracks_per_ticket = 3000;

    private String post_login;
    private String post_pass;
    private String post_url;
    private int track_per_ticket;

    private SoapClient soapClient;

    public RussianPostClient(String post_login, String post_pass, String post_url, int track_per_ticket){
        super();
        this.post_login = post_login;
        this.post_pass = post_pass;
        this.post_url = post_url;
        this.track_per_ticket = track_per_ticket;

        soapClient = new SoapClient();
    }//RussianPostClient

    public RussianPostClient(String post_login, String post_pass){
        super();
        this.post_login = post_login;
        this.post_pass = post_pass;
        this.post_url = def_post_url;
        this.track_per_ticket = max_tracks_per_ticket;

        soapClient = new SoapClient();
    }//RussianPostClient

    public void getTicketStatus(ArrayList<RussianPostTicket> tickets){
        //return "";
    }//getTicketStatus

    private RussianPostTicket makeTicket(ArrayList<String> trackcodepackets){
        return new RussianPostTicket();
    }//makeTicket

    public ArrayList<RussianPostTicket> makeTickets(String[] trackcodes){

        ArrayList<RussianPostTicket> ticketarray = new ArrayList<RussianPostTicket>();
        ArrayList<String> track_packet = new ArrayList<String>();

        int counter = 0;
        while(counter <= trackcodes.length){
            track_packet.add(trackcodes[counter]);
            counter++;

            if(counter == track_per_ticket){
                RussianPostTicket ticket = makeTicket(track_packet);
                ticketarray.add(ticket);
                track_packet.clear();
            }
        }
        return ticketarray;
    }//makeTickets
}
