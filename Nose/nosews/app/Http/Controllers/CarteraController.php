<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Cartera;
use App\Models\Cliente;
use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of CarteraController
 *
 * @author alejo
 */
class CarteraController extends Controller {

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["cliente"])->first();
                $local = \App\Models\Local::where("external_id", $data["local"])->first();
                if ($cliente == true && $local == true) {
                    $cartera = new Cartera();
                    $cartera->local()->associate($local);
                    $cartera->cliente()->associate($cliente);
                    $cartera->external_id = utilidades\UUID::v4();
                    $cartera->saldo = $data["saldo"];
                    $cartera->save();
                    return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
                } else {
                    return response()->json(["mensaje" => "Referencia Incorrecta", "siglas" => "RI"], 400);
                }
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

    public function modificar(Request $request, $external_id) {//el request es solo para post, get y put
        $carObj = Cartera::where("external_id", $external_id)->first(); //busca el registro por el primer external_id

        if ($carObj) {//verifica que exista el administrador
            if ($request->isJson()) {//verifica que este en formato json
                $data = $request->json()->all(); //estrae todo del administrador
                $cartera = Cartera::find($carObj->id); //para que busque el id con el external id

                if (isset($data["saldo"]))
                    $cartera->saldo = $data["saldo"];

                $cartera->save();
                return response()->json(["mensaje" => "Operacion Exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "Formato Inadecuado", "siglas" => "FI"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No registros", "siglas" => "NR"], 203);
        }
    }

    public function Listar() {
        $lista = Cartera::orderBy('id_local')->get();
        $listaLocal = Local::orderBy('nombre')->get();
        $listaCli = Cliente::orderBy('nombres')->get();
        $data = array();

        foreach ($listaLocal as $valueloc) {
            $nombreloc = $valueloc->nombre;
            foreach ($listaCli as $valuecli) {
                $nombrecli = $valuecli->nombres;
                foreach ($lista as $value) {
                    $data[] = ["<br>" . "nombre del local" => $nombreloc, "nombre cliente" => $nombrecli,
                        "saldo" => $value->saldo];
                }
            }
        }
        return $data;
    }

}
