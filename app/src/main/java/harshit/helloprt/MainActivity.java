package harshit.helloprt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.*;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;




import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    SocketIO socket;
    Button b,b1,b3;
    EditText e;
    TextView t;
    Socket sock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        b1=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        e=(EditText)findViewById(R.id.editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    // this is the button when pressed it will connect to server
                socket = new SocketIO();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=e.getText().toString();
                socket.sendMessage(s);            // it is the button when pressed it sends msg
            }
        });
    }
    public class SocketIO{
        public SocketIO(){
            try{
                sock = IO.socket("http://31.220.111.101:3000/");
                sock.connect();  // connect to server
                Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
                sock.on("new message",msg);        // it is being made to recieve msg
            } catch(Exception e){
                Toast.makeText(getApplicationContext(),"Got a exception",Toast.LENGTH_LONG).show();
            }
        }
        public void sendMessage(String s)
        {
            Toast.makeText(getApplicationContext(),"Sent ",Toast.LENGTH_LONG).show();
            sock.emit("send message",s);
        }

    }
    private Emitter.Listener msg=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            t.setText("Some message received ");
        }
    };
}


