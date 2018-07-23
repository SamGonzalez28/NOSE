<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of LocalController
 *
 * @author AlejandroC
 */
class LocalController extends Controller {

    private $external_id;

   

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $local = new Local();
                $local->nombre = $data["nombre"];
                $local->direccion = $data["direccion"];
                $local->ruc = $data["ruc"];
                $local->clave = $data["clave"];
                $local->telefono = $data["telefono"];
                $local->external_id = utilidades\UUID::v4();

                $local->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

    public function inicioSesion(Request $request) {

        try {
            $data = $request->json()->all();
            $local = Local::where("nombre", $data["nombre"])->where("clave", $data["clave"])->where("estado", true)->first();

            if ($local) {
                return response()->json(["nombre" => $local->nombre,
                            "id" => $local->external_id,
                            "nombre" => $local->nombre,
                            "token" => base64_encode($local->external_id . '--' . $local->nombre),
                            "mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
            }
        } catch (\Exception $exc) {
            return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
        }
    }

    public function modificar(Request $request, $external_id) {
        $localObjeto = Local::where("external_id", $external_id)->first();
        if ($localObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $local = Local::find($localObjeto->id_local);
                if (isset($data["nombre"]))
                    $local->nombre = $data["nombre"];
                if (isset($data["direccion"]))
                    $local->nombre = $data["direccion"];
                if (isset($data["ruc"]))
                    $local->nombre = $data["ruc"];
                if (isset($data["clave"]))
                    $local->clave = $data["clave"];
                if (isset($data["telefono"]))
                    $local->nombre = $data["telefono"];

                $local->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    public function eliminar(Request $request, $external_id) {
        $localObjeto = Local::where("external_id", $external_id)->first();
        if ($localObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $local = Local::find($localObjeto->id_local);
                if (isset($data["estado"]))
                    $local->estado = $data["estado"];
                $local->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

}