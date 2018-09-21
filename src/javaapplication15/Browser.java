
package javaapplication15;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javax.swing.*;
import javax.swing.event.*;

public class Browser {  
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 700;
    private static final String home = "http://ysu.edu.cn";//默认主页
    private static final String url_head = "http://";
    
    public static void main(String[] args) 
    {

	JFrame frame = new JFrame("计算机网络一班第三组-JavaFX实现的简易浏览器");
	final JFXPanel webBrowser = new JFXPanel();
	frame.add(webBrowser);       
      
	Platform.runLater(new Runnable() 
        {  
            public void run() 
            {		
                StackPane root = new StackPane();//各种组件的布局
		Scene scene = new Scene(root);//新建场景
		webBrowser.setScene(scene);
                Button BACK = new Button("后退");
                Button FORWORD = new Button("前进");
                Button SHUAXIN = new Button("刷新");
                Button GOHOME = new Button("主页"); 
                Button GO = new Button("转到");
                TextField InputField = new TextField();//输入框    

		VBox vbox = new VBox(10);//纵向布局
		HBox lBox = new HBox(10);//横向布局
                
		InputField.setText(home);
		InputField.setPrefWidth(WIDTH - 1000);
		lBox.getChildren().addAll(BACK,FORWORD,SHUAXIN,GOHOME,InputField,GO);//输入框与按钮放在同一横式布局

		WebView main_view = new WebView();//浏览器主显示面板
	        WebEngine engine = main_view.getEngine();//渲染引擎
                final WebHistory history = engine.getHistory();   //浏览历史
		engine.load(home);
		vbox.getChildren().addAll(lBox,main_view);//三个组件放在纵式布局中
		root.getChildren().add(vbox);//所有布局组件置于堆栈面板

		GO.setOnAction(new EventHandler<ActionEvent>()//转到按钮监视器
                {
                    public void handle(ActionEvent event) 
                    {				
                        if (!InputField.getText().startsWith(url_head)) 
                        {
                            engine.load(url_head + InputField.getText());//如果url不是http://开头,加上
                            //urlField.setText(urll);
                        } 
                        else 
                        {		
                            engine.load(InputField.getText());
                        }
                    }
		});
                
		BACK.setOnAction(new EventHandler<ActionEvent>()//监视器
                {
                    public void handle(ActionEvent event) 
                    {	
                        history.getCurrentIndex();//后退
                        history.go(-1);
                    }
		});
                
		FORWORD.setOnAction(new EventHandler<ActionEvent>()//监视器
                {
                    public void handle(ActionEvent event) 
                    {				
                        history.getCurrentIndex();//前进
                        history.go(1);
                    }
		});
                
		SHUAXIN.setOnAction(new EventHandler<ActionEvent>()//监视器
                {
                    public void handle(ActionEvent event) 
                    {				
                         engine.reload();//刷新
                    }
		});
                
		GOHOME.setOnAction(new EventHandler<ActionEvent>()//监视器
                {
                    public void handle(ActionEvent event) 
                    {				
                         engine.load(home);//返回主页
                    }
		});              
            }
	});

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	}  

}