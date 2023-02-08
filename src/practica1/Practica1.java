/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
/**
 *
 * @author Javier
 */

public class Practica1 {
    
    
public static Scanner entrada = new Scanner(System.in);


public static String [][] productosAlmacenados = new String [20][2];
public static int id = 0;

public static String [][] codDescuento = new String [20][2];
public static int idCod = 0;

public static String repetir = "1";
public static String opcion = "";

public static int ven = 0;
public static String [][] reporte= new String [20][2];
public static int idVentas = 0;


public static String [][] ventasHechas = new String [20][2];

public static float resultado = 0;
public static String usuario="";

public static int [] reporteInt= new int [20];
public static String [] reporteProductos= new String [20];





    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // validando usuarios
        boolean entro = false;
        while(entro==false){
        escribir("---------------------");
        escribir("SUPER-25");
        escribir("");
        usuario = obtenerDato("Ingrese el nombre de usuario");
        String contraseña = obtenerDato("Ingrese la contrasena");
        
        
       //codigo donde valida el usuario ingresado
       
        if(usuario.equals("cajero_202200392") && contraseña.equals("ipc1_202200392") ){
            escribir("Bienvenido: "+ usuario);
             entro = true;
             
            menuPrincipal();
            
        }else{
            escribir("Contrasena o usuario incorrectos");
            escribir("Vuelva a intentarlo");
        }
        }        
    }
    
    //Metodo para enviarnos al menu principal y que realice todas las funciones de los demas metodos
    public static void menuPrincipal() {
         while(repetir.equals("1")){
             menu();
             seleccion();
                 
                 if(repetir.equals("2")){
                     break;
                 }
             escribir("Desea realizar alguna otra accion?");
             repetir = obtenerDato("1. si, 2. no");

             }
    }
    
    
    
    
    //Switch donde se evaluaran la opcion que eligio el usuario
    public static void seleccion(){
        boolean opVal = false;
        while(opVal==false){
        if(opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4") || opcion.equals("5") ){
            opVal= true;
        switch (opcion) {
            case "1": agregarNuevosProductos();
                
                break;
            case "2": agregarCupones();
                
                break;
                
            case "3":
                realizarVenta();
                break;
            case "4": 
                ordenar(reporteInt, reporteProductos);
                
                break;
            default:
                 repetir="2";
                  
                break;
        }
        }else{
            escribir("Ingrese una opcion valida");
            opcion= obtenerDato("Ingrese el numero de la opcion");
            
        }
        }
    }
    
    //Metodo para el ingreso de nuevos productos
    public static void agregarNuevosProductos(){
     
        boolean precioValido = false;
        while(precioValido==false){
        escribir("");
        String producto = obtenerDato("Ingrese el nombre del producto");
        String precio = obtenerDato("Ingrese el precio del producto en quetzales");
        escribir("");
       
        if(Float.parseFloat(precio)!= 0){
        if(existencia(productosAlmacenados,producto) == -1){
        productosAlmacenados[id][0]=producto;
        productosAlmacenados[id][1]=precio;
        precioValido = true;
        id++;   
         escribir("-----------------------------");
         escribir("Productos registrados hasta el momento: ");
         escribir("");
        verArray(productosAlmacenados, "Producto", "Precio","","Q");
        }
        else{
            escribir("Ya esta registrado el producto con el nombre: "+ producto);
            escribir("Volviendo al menu principal...");
            menuPrincipal();
            precioValido = true;
        }}
        else{
            escribir("El precio del producto no puede ser Q 0.00");
        }
        }
    }
   
            
    
    
    
    
    //Metodo donde indicara las opciones para el usuario
    public static void menu(){
       escribir("------------------------------");
       escribir("Bienvenido al sistema");
       escribir("Puede realizar las siguientes opciones");
       escribir("1. Agregar nuevos productos");
       escribir("2. Agregar cupones de descuento");
       escribir("3. Realizar ventas");
       escribir("4. Realizar reporte");
       escribir("5. salir");
       escribir("");
       opcion= obtenerDato("Ingrese el numero de la opcion");
       escribir("-------------------------------"); 
    }
    
    
    
    //Metodo para agregar cupones
    
        public static void agregarCupones(){
        
        boolean descValido = false;
        while(descValido==false){
        escribir("");
        String codigoDes = obtenerDato("Ingrese el codigo de descuento de 4 caracteres");
        String desc = obtenerDato("Ingrese el porcentaje de descuento");
        escribir("");
        if(Float.parseFloat(desc)!=0 && Float.parseFloat(desc)<100 && contar(codigoDes)==4){
        if(existencia(codDescuento,codigoDes) == -1){
            
            
        codDescuento[idCod][0]= codigoDes;
        codDescuento[idCod][1]=String.valueOf(desc);
        descValido = true;
        idCod++;   
        escribir("-----------------------------");
        escribir("Codigos de descuentos registrados hasta el momento: ");
        verArray(codDescuento, "Codigo", "Porcentaje","%","");
        }
        else{
            escribir("Ya esta registrado el codigo: "+ codigoDes);
            escribir("Volviendo al menu principal...");
            menuPrincipal();
            descValido=true;
            
        }}
        else{
            escribir("Codigo no puede contener mas de 4 caracteres");
            escribir("y el descuento no puede ser de 0%.");
            escribir("Vuelva a intentarlo");
            
        }
        }
        
    }
        
        //Funcion para contar la cantidad de caracteres que contiene un String
        public static int contar(String cantidad) {
            int nLetras = 0;
            for (int i = 0; i < cantidad.length(); i++) {
                if(Character.isLetter(cantidad.charAt(i))){
                    nLetras++;
                }
            }
            return nLetras;
    }
        
        
        
        
        
        // Metodo para validar si existe el algun dato del array/arreglo
         public static int existencia(String array[][],String existe){
        for(int i = 0; i < array.length; i++){
            if(array[i][0]!=null){
            if(array[i][0].equals(existe)){
                return i;
            }}else{
                return -1;
            }
        }
        return -1;
    }
        
        
        
        
        
        //Metodo para ver un array/arreglo
         public static void verArray(String arreglo[][], String dato1, String dato2,String simbolo,String simbolo2){
        
        for(int i = 0; i< arreglo.length; i++){
            
            if(arreglo[i][0]!= null){
            System.out.print(i+1+"."+dato1+": "+ arreglo[i][0]+" ");
            System.out.println(dato2+": "+simbolo2+ arreglo[i][1]+simbolo+" ");
            escribir("");
            
            
            }
        }
    }
    
    //Metodo para realizar ventas
         static void realizarVenta (){
             String op="";
             
             float totalPagar = 0;
             String idCompra;
             
             String nombreComprador = obtenerDato("Ingrese el nombre de la persona");
             String nit = obtenerDato("Ingrese el nit o enter para C/F");
             if (nit.equals("")){
                 nit = "C/F";
             }
             escribir("----------------------------");
             escribir("Seleccione el producto y la cantidad que desea comprar:");
             escribir("");
             verArray(productosAlmacenados, "Producto", "Precio","","Q");
             boolean finalizar= false;
            while(finalizar==false){
                String cantidad=null;
                escribir("");
                 idCompra= obtenerDato("Ingrese el numero del producto que aparece en la lista");
                 idVentas= parseInt(idCompra)-1;
                cantidad =obtenerDato("Ingrese la cantidad que desea comprar");
                 
               resultado=Float.valueOf(productosAlmacenados[idVentas][1])*Float.valueOf(cantidad);
               
               
                reporte [ven][0] = productosAlmacenados[idVentas][0];
                reporte [ven][1] = String.valueOf(cantidad);
                
               
                
               
                 ventasHechas[ven][0]= productosAlmacenados[idVentas][0];
                 ventasHechas[ven][1] = String.valueOf(resultado);
                 
                 
                 ven++;
                 totalPagar=resultado+totalPagar;
                 
                 escribir("Desea agregar algun otro producto?");
                 op = obtenerDato("1. Si, 2.No");
                 
                 if(op.equals("2")){
                     while(finalizar==false){
                     escribir("Ingrese algun codigo de descuento");
                     String codig = obtenerDato("o presione enter para no agregar codigo");
                     if((contar(codig)==4 || codig.equals(""))&& buscar(codig)==true){
                         
                     emitirFactura(totalPagar, nit, nombreComprador, codig);
                     reporte(reporte,reporteProductos);
                     finalizar=true;
                     }
                     else{
                         escribir("ERROR!:");
                         escribir("El codigo ingresado no existe o, es menor/mayor a 4 caracteres");
                         escribir("");
                     }
                     }
                     
                 }
                 else if(op.equals("1")){
                     finalizar=false;
                 }
                 else if (op.equals("0")){
                     escribir("Error!, vuelva a realizar el proceso");
                     escribir("");
                     ventasHechas[ven-1][0]= null;
                 ventasHechas[ven-1][1] = null;
                    reporte [ven-1][0] = null;
                 reporte [ven-1][1] = null;
                 }
                 
                 
                 
             }
             
               
                 
             
         }
         
         
        
        
    
    
    //Metodo para la emision de la factura
         public static void emitirFactura(float total, String nit, String nom, String codi) {
             escribir("------------------------------");
             escribir("           SUPER-25");
             escribir("------------------------------");
             escribir("Cajero: "+ usuario);
             escribir("Fecha: 10/02/2023");
             escribir("Nombre del comprador: "+ nom);
             escribir("NIT: "+nit);
             escribir("------------------------------");
             for(int i = 0; i< ventasHechas.length; i++){
            
            if(ventasHechas[i][0]!= null){
            System.out.print(" "+ reporte[i][1]+" ");
            System.out.print(" "+ ventasHechas[i][0]+" ");
            System.out.println("        Q "+ ventasHechas[i][1]+" ");
            escribir("");
            
            
            }
                 
        }
             escribir("------------------------------");
             if(codi.equals("")){
             escribir("Descuento del 0%");
             escribir("             TOTAL: Q"+total);
             escribir("     TOTAL A PAGAR: Q"+total);
             }else{
                 buscar(codi);
                 float total2=buscarCod(codi)*total;
                 float total3= total-total2;
             escribir("             TOTAL: Q"+total);
             escribir("     TOTAL A PAGAR: Q"+ total3);
                 
             }
             escribir("");
             vaciar(ventasHechas);
             ven=0;
    }
         
    //Metodo para buscar el codigo de descuento
          public static float buscarCod(String cod){
        for(int i = 0; i < codDescuento.length; i++){
            if(codDescuento[i][0]!=null){
            if(codDescuento[i][0].equals(cod)){
                float convertir= Float.parseFloat(codDescuento[i][1])/100;
                return convertir;
            }}
        }
        return -1;
    }
           public static boolean buscar(String cod){
        for(int i = 0; i < codDescuento.length; i++){
            if(codDescuento[i][0]!=null){
            if(codDescuento[i][0].equals(cod)){
                escribir("Descuento del "+codDescuento[i][1]+"%");
                    break;
                   
            }}else if(cod.equals("")){
                
                return true;
            }else{
                return false;
            }
        }
        return true;
    }
        
    
    //Metodo para vaciar un arreglo
           public static void vaciar(String arreglo[][]) {
                for(int i = 0; i< arreglo.length; i++){
            
            if(arreglo[i][0]!= null){
            arreglo[i][0]=null;
            arreglo[i][1]=null;
            }}
        
    }
     
    //Metodo para realizar el reporte
    public static void reporte(String arreglo[][], String arreglo2[]){
     int j = 0;
        int contar=0;
       
        for(int i = 0; i< arreglo.length; i++){ 
            contar=0;
            
             if(arreglo[i][0]!= null){
                 
            for ( j = 0; j < arreglo.length; j++) {
                
                
                if(arreglo[i][0].equals(reporteProductos[j]) && reporteProductos[j]!=null){
                    
           
         reporteInt[j]+=parseInt(arreglo[i][1]);
         break;
         
         
                }
                
                
                else if(reporteProductos[j]==null){
                    
                    contar=parseInt(arreglo[j][1])+contar;
                     reporteInt[i]=contar;
                     reporteProductos[i]=arreglo[i][0]; 
                     break;
         
                    
                } 
            }
         
           
             
            }
            
              
        
        }
       
        
            vaciar(reporte);
            
            
            
             
             
    }
           
    //Metodo para ordenar los productos de forma descendente
     public static void ordenar(int arreglo[], String arreglo2[]) {
         for (int i = 0; i < arreglo.length; i++) {
             if(String.valueOf(arreglo[i]) != null){
                 
             for (int j = 0; j < arreglo.length-1; j++) {
                 if(arreglo[j]<arreglo[j+1]){
                     String aux2;
                     aux2=arreglo2[j];
                     arreglo2[j]=arreglo2[j+1];
                     arreglo2[j+1]=aux2;
                     int aux;
                     aux=arreglo[j];
                     arreglo[j]=arreglo[j+1];
                     arreglo[j+1]=aux;
                     
                     
                     
                 }
                 
                     
                 
             }
         }
    }
         escribir("------------------------------");
         escribir("Reporte diario:");
         escribir("");
         verArray2(arreglo2, arreglo);
         
  }
    
           
     
     
     
     
     
     public static void verArray2(String arreglo[], int arreglo2[]){
       
        for(int i = 0; i< arreglo.length; i++){
            
            if(arreglo[i]!= null){
            System.out.print(i+1+" "+ arreglo[i]);
            System.out.println(" "+arreglo2[i]);
            escribir("");
            
            
            }
        }
    }
      
     
    
    // Metodo para ingresar un dato desde el teclado
     public static String obtenerDato(String msj){
        escribir(msj);
        return entrada.nextLine();
    }
     
    //Metodo para que sea mas facil escribir datos en consola
    public static void escribir(String texto){
        System.out.println(texto);
    }
    
    
    
}
