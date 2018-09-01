<?php
namespace App\Http\Controllers;

use App\Models\Cliente;
use Illuminate\Http\Request;

/**
 * Description of ClienteController
 * Clase usada para controlar las funciones que se aplican a la tabla cliente
 *
 * @author AlejandroC
 */
class ClienteController extends Controller {

    /**
     *Variable que guarda el external id, que reciben las funciones que aqui se usan 
     * @var type 
     */
    private $external_id;


    /**
     * Funcion que se encarga de registrar un Cliente
     * @param Request $request
     * @return type
     */
    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $client = new Cliente();
                $client->user = $data["user"];
                $client->clave = $data["clave"];
                $client->nombres = $data["nombres"];
                $client->apellidos = $data["apellidos"];
                $client->ci = $data["ci"];
                $client->correo = $data["correo"];
                $client->direccion = $data["direccion"];
                $client->telefono = $data["telefono"];
                $client->estado = true;
                $client->external_id = utilidades\UUID::v4();

                $client->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos " + $ex, "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }
    
    /**
     * Funcion para que cliente inicie sesion
     * @param Request $request
     * @return type
     */
    public function inicioSesion(Request $request) {

        try {
            $data = $request->json()->all();
            $cliente = Cliente::where("user", $data["user"])
                            ->where("clave", $data["clave"])
                            ->where("estado", true)->first();

            if ($cliente) {
                return response()->json(["user" => $cliente->user,
                            "external_id" => $cliente->external_id,
                            "nombres" => $cliente->nombres,
                            "token" => base64_encode($cliente->external_id . '--' . $cliente->user),
                            "mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
            }
        } catch (\Exception $exc) {
            return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
        }
    }

    /**
     * Funcion para modificar los datos de cliente
     * @param Request $request
     * @param type $external_id el external de cliente que se va a modificar
     * @return type
     */
    public function modificar(Request $request, $external_id) {
        $clienteObjeto = Cliente::where("external_id", $external_id)->first();
        if ($clienteObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $client = Cliente::find($clienteObjeto->id);
                if (isset($data["user"]))
                    $client->user = $data["user"];
                if (isset($data["clave"]))
                    $client->clave = $data["clave"];
                if (isset($data["nombres"]))
                    $client->nombres = $data["nombres"];
                if (isset($data["apellidos"]))
                    $client->apellidos = $data["apellidos"];
                if (isset($data["correo"]))
                    $client->correo = $data["correo"];
                if (isset($data["direccion"]))
                    $client->direccion = $data["direccion"];
                if (isset($data["telefono"]))
                    $client->telefono = $data["telefono"];
                if (isset($data["ci"]))
                    $client->ci = $data["ci"];

                $client->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Funcion para eliminar de manera logica, un perfil de cliente
     * @param Request $request
     * @param type $external_id del cliente que se va a eliminar
     * @return type
     */
    public function eliminar(Request $request, $external_id) {
        $clientObjeto = Cliente::where("external_id", $external_id)->first();
        if ($clientObjeto) {
            if ($request->isJson()) {

                $client = Cliente::find($clientObjeto->id);

                $client->estado = false;
                $client->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }
    
    /**
     * Funcion para listar todos los clientes con perfil activo(que no haya sido eliminado)
     * @return type
     */
    public function Listar() {
        $lista = Cliente::where('estado','=','1')->orderBy('nombres')->get();
        $data = array();

        foreach ($lista as $value) {

            $data[] = ["nombres" => $value->nombres, "apellidos" => $value->apellidos, "ci" => $value->ci,
                "correo" => $value->correo, "direccion" => $value->direccion, "telefono" => $value->telefono,
                "user" => $value->user];
        }
        return $data;
    }
    
    /**
     * Funcion para ver los datos del cliente que ha iniciado sesion
     * @param type $external_id del cliente que inicio sesion
     * @return type
     */
    public function ver($external_id) {
        $lista = Cliente::where("external_id", $external_id)->get();
        $data = array();
        foreach ($lista as $value) {

            $data[] = ["nombres" => $value->nombres, "apellidos" => $value->apellidos, "ci" => $value->ci,
                "correo" => $value->correo, "direccion" => $value->direccion, "telefono" => $value->telefono,
                "user" => $value->user];
        }
        return $data;
    }

}
