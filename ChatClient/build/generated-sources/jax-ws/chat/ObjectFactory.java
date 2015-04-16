
package chat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the chat package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AdminLogon_QNAME = new QName("http://server.chat/", "adminLogon");
    private final static QName _BroadcastResponse_QNAME = new QName("http://server.chat/", "broadcastResponse");
    private final static QName _ConnectedUsersResponse_QNAME = new QName("http://server.chat/", "connectedUsersResponse");
    private final static QName _Broadcast_QNAME = new QName("http://server.chat/", "broadcast");
    private final static QName _AdminLogoffResponse_QNAME = new QName("http://server.chat/", "adminLogoffResponse");
    private final static QName _ListenResponse_QNAME = new QName("http://server.chat/", "listenResponse");
    private final static QName _PrivateMessageResponse_QNAME = new QName("http://server.chat/", "privateMessageResponse");
    private final static QName _LeaveResponse_QNAME = new QName("http://server.chat/", "leaveResponse");
    private final static QName _Listen_QNAME = new QName("http://server.chat/", "listen");
    private final static QName _Leave_QNAME = new QName("http://server.chat/", "leave");
    private final static QName _AdminLogoff_QNAME = new QName("http://server.chat/", "adminLogoff");
    private final static QName _Join_QNAME = new QName("http://server.chat/", "join");
    private final static QName _JoinResponse_QNAME = new QName("http://server.chat/", "joinResponse");
    private final static QName _ConnectedUsers_QNAME = new QName("http://server.chat/", "connectedUsers");
    private final static QName _AdminLogonResponse_QNAME = new QName("http://server.chat/", "adminLogonResponse");
    private final static QName _PrivateMessage_QNAME = new QName("http://server.chat/", "privateMessage");
    private final static QName _PrivateMessageArg3_QNAME = new QName("", "arg3");
    private final static QName _BroadcastArg2_QNAME = new QName("", "arg2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: chat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Broadcast }
     * 
     */
    public Broadcast createBroadcast() {
        return new Broadcast();
    }

    /**
     * Create an instance of {@link AdminLogon }
     * 
     */
    public AdminLogon createAdminLogon() {
        return new AdminLogon();
    }

    /**
     * Create an instance of {@link BroadcastResponse }
     * 
     */
    public BroadcastResponse createBroadcastResponse() {
        return new BroadcastResponse();
    }

    /**
     * Create an instance of {@link ConnectedUsersResponse }
     * 
     */
    public ConnectedUsersResponse createConnectedUsersResponse() {
        return new ConnectedUsersResponse();
    }

    /**
     * Create an instance of {@link AdminLogoffResponse }
     * 
     */
    public AdminLogoffResponse createAdminLogoffResponse() {
        return new AdminLogoffResponse();
    }

    /**
     * Create an instance of {@link ListenResponse }
     * 
     */
    public ListenResponse createListenResponse() {
        return new ListenResponse();
    }

    /**
     * Create an instance of {@link PrivateMessageResponse }
     * 
     */
    public PrivateMessageResponse createPrivateMessageResponse() {
        return new PrivateMessageResponse();
    }

    /**
     * Create an instance of {@link LeaveResponse }
     * 
     */
    public LeaveResponse createLeaveResponse() {
        return new LeaveResponse();
    }

    /**
     * Create an instance of {@link Listen }
     * 
     */
    public Listen createListen() {
        return new Listen();
    }

    /**
     * Create an instance of {@link Leave }
     * 
     */
    public Leave createLeave() {
        return new Leave();
    }

    /**
     * Create an instance of {@link ConnectedUsers }
     * 
     */
    public ConnectedUsers createConnectedUsers() {
        return new ConnectedUsers();
    }

    /**
     * Create an instance of {@link AdminLogoff }
     * 
     */
    public AdminLogoff createAdminLogoff() {
        return new AdminLogoff();
    }

    /**
     * Create an instance of {@link Join }
     * 
     */
    public Join createJoin() {
        return new Join();
    }

    /**
     * Create an instance of {@link JoinResponse }
     * 
     */
    public JoinResponse createJoinResponse() {
        return new JoinResponse();
    }

    /**
     * Create an instance of {@link AdminLogonResponse }
     * 
     */
    public AdminLogonResponse createAdminLogonResponse() {
        return new AdminLogonResponse();
    }

    /**
     * Create an instance of {@link PrivateMessage }
     * 
     */
    public PrivateMessage createPrivateMessage() {
        return new PrivateMessage();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminLogon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "adminLogon")
    public JAXBElement<AdminLogon> createAdminLogon(AdminLogon value) {
        return new JAXBElement<AdminLogon>(_AdminLogon_QNAME, AdminLogon.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BroadcastResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "broadcastResponse")
    public JAXBElement<BroadcastResponse> createBroadcastResponse(BroadcastResponse value) {
        return new JAXBElement<BroadcastResponse>(_BroadcastResponse_QNAME, BroadcastResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectedUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "connectedUsersResponse")
    public JAXBElement<ConnectedUsersResponse> createConnectedUsersResponse(ConnectedUsersResponse value) {
        return new JAXBElement<ConnectedUsersResponse>(_ConnectedUsersResponse_QNAME, ConnectedUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Broadcast }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "broadcast")
    public JAXBElement<Broadcast> createBroadcast(Broadcast value) {
        return new JAXBElement<Broadcast>(_Broadcast_QNAME, Broadcast.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminLogoffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "adminLogoffResponse")
    public JAXBElement<AdminLogoffResponse> createAdminLogoffResponse(AdminLogoffResponse value) {
        return new JAXBElement<AdminLogoffResponse>(_AdminLogoffResponse_QNAME, AdminLogoffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "listenResponse")
    public JAXBElement<ListenResponse> createListenResponse(ListenResponse value) {
        return new JAXBElement<ListenResponse>(_ListenResponse_QNAME, ListenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrivateMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "privateMessageResponse")
    public JAXBElement<PrivateMessageResponse> createPrivateMessageResponse(PrivateMessageResponse value) {
        return new JAXBElement<PrivateMessageResponse>(_PrivateMessageResponse_QNAME, PrivateMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LeaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "leaveResponse")
    public JAXBElement<LeaveResponse> createLeaveResponse(LeaveResponse value) {
        return new JAXBElement<LeaveResponse>(_LeaveResponse_QNAME, LeaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Listen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "listen")
    public JAXBElement<Listen> createListen(Listen value) {
        return new JAXBElement<Listen>(_Listen_QNAME, Listen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Leave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "leave")
    public JAXBElement<Leave> createLeave(Leave value) {
        return new JAXBElement<Leave>(_Leave_QNAME, Leave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminLogoff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "adminLogoff")
    public JAXBElement<AdminLogoff> createAdminLogoff(AdminLogoff value) {
        return new JAXBElement<AdminLogoff>(_AdminLogoff_QNAME, AdminLogoff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Join }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "join")
    public JAXBElement<Join> createJoin(Join value) {
        return new JAXBElement<Join>(_Join_QNAME, Join.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "joinResponse")
    public JAXBElement<JoinResponse> createJoinResponse(JoinResponse value) {
        return new JAXBElement<JoinResponse>(_JoinResponse_QNAME, JoinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectedUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "connectedUsers")
    public JAXBElement<ConnectedUsers> createConnectedUsers(ConnectedUsers value) {
        return new JAXBElement<ConnectedUsers>(_ConnectedUsers_QNAME, ConnectedUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminLogonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "adminLogonResponse")
    public JAXBElement<AdminLogonResponse> createAdminLogonResponse(AdminLogonResponse value) {
        return new JAXBElement<AdminLogonResponse>(_AdminLogonResponse_QNAME, AdminLogonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrivateMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.chat/", name = "privateMessage")
    public JAXBElement<PrivateMessage> createPrivateMessage(PrivateMessage value) {
        return new JAXBElement<PrivateMessage>(_PrivateMessage_QNAME, PrivateMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg3", scope = PrivateMessage.class)
    public JAXBElement<byte[]> createPrivateMessageArg3(byte[] value) {
        return new JAXBElement<byte[]>(_PrivateMessageArg3_QNAME, byte[].class, PrivateMessage.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg2", scope = Broadcast.class)
    public JAXBElement<byte[]> createBroadcastArg2(byte[] value) {
        return new JAXBElement<byte[]>(_BroadcastArg2_QNAME, byte[].class, Broadcast.class, ((byte[]) value));
    }

}
