package com.qrcodetest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQrcode {
	
	//CreateQrcode�Ĺ��췽��
	CreateQrcode(){
		
		Qrcode qrocde = new Qrcode();
		
		//���ö�ά��Ľ����ַ�
		qrocde.setQrcodeEncodeMode('B');
		//���ö�ά����ݴ���
		qrocde.setQrcodeErrorCorrect('M');
		
		String base64 = "iVBORw0KGgoAAAANSUhEUgAAABkAAAAUCAMAAABPqWaPAAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAAAeUExURWZmYzYuKz40L29ubIiIhn18eV5dWiklIktHRBAMC8Ud5soAAADHSURBVCjPVZALkgQhCEMBJeD9LzwBdHaHtqpLngkfcV+LhyGZkMXvRgoZj6RqRIrwST0TUUUBpBoJLxMkoQZ3KsyUKkt5hFeD4AKiV0ZgtGG+f0T0u034KqMO1fYbgOosf0g+N2f5v7xqoiRAE69aF9ELZQU08ax8o1xThWSPaFpj/ddZk+35rRQ16XSw9/ad/7uu3V0yGrXZXPREa8iWN5EF45h8yWpFIebPCRRqst8aKn/yLDyNY3anTZDs+RLaDSkQtQLBB1l/BcZMoSCnAAAAAElFTkSuQmCC";
		String temp = "BEGIN:VCARD\n" + 
				  "PHOTO;ENCODING=b:"+base64+"\n"+ 
				  "FN:������\n" + 
				  "ORG:�ӱ��Ƽ�ʦ��ѧԺ\n" + 
				  "TITLE:ѧ��\n" + 
				  "ADR;WORK:�ػʵ��������ӱ����360��\n" + 
				  "ADR;HOME:�ӱ��Ƽ�ʦ��ѧԺ\n" + 
				  "TEL;CELL,VOICE:11111111111\n" + 
				  "TEL;WORK,VOICE:11111111111\r\n" + 
				  "URL;WORK;:http://www.hevttc.edu.cn\n" + 
				  "EMAIL;INTERNET,HOME:2995862784@qq.com\n" + 
				  "END:VCARD";
		
		boolean[][] countcode = qrocde.calQrcode(temp.getBytes());
		int codesize = 67+12*(qrocde.getQrcodeVersion() - 1);
		
		BufferedImage buffer = new BufferedImage(codesize, codesize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gra = buffer.createGraphics();
		//�޸Ļ�����ɫ���������
		gra.setBackground(Color.WHITE);
		gra.setColor(Color.BLACK);
		gra.clearRect(0, 0, codesize, codesize);
		
		int startR = 235;
		int startG = 125;
		int startB = 16;
		int endR = 12;
		int endG = 239;
		int endB = 120;
		
		
		for (int i = 0; i < countcode.length; i++) {
			for (int j = 0; j < countcode.length; j++) {
				if(countcode[i][j]){
					int r = startR + (endR - startR) * (i+1)/countcode.length;
					int g = startG + (endG - startG) * (i+1)/countcode.length;
					int b = startB + (endB - startB) * (i+1)/countcode.length;
					Color color = new Color(r,g,b);
					gra.setColor(color);
					
					gra.fillRect(i*3+2, j*3+2, 3, 3);
				}
			}
		}
		
		
		try {
			/**
			 * programpath����Ŀ����·��
			 * logopath���������õ�logo��·��
			 * codepath���������ɶ�ά���·��
			 * logo�����ö�ά���м��logoͼ��
			 * logosize��logo�Ĵ�С
			 * logox��logo��XY����
			 */
			File dir = new File("");
			//��ȡ��ǰ��Ŀ�����ļ������·��
			String programpath= dir.getCanonicalPath();
			String logopath = programpath + "\\" + "logo.png";
			String codepath = programpath + "\\";
			
			Image logo = ImageIO.read(new File(logopath));
			int logosize = codesize/4;
			int logox = (codesize-logosize)/2;
			gra.drawImage(logo, logox, logox, logosize, logosize, null);
			
			gra.dispose();
			buffer.flush();
			
			ImageIO.write(buffer, "png", new File(codepath+"code.png"));
			System.out.println("���ɵĶ�ά���·���ǣ� "+codepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//����һ��CreateQRcode��ʵ������
		CreateQrcode cq = new CreateQrcode();
	}
	
}
