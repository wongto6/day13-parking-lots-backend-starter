import axios from "axios";


export const http = axios.create(
    {baseURL: "http://localhost:8080", timeout: 5000}
)

export const park = async (plateNumber, boyType) => {
    const response = await http.post("/parkinglot/park", {plateNumber: plateNumber, boyType: boyType})
    return response.data
}

export const fetch = async (plateNumber, boyType) => {
    const response = await http.post("/parkinglot/fetch", {plateNumber: plateNumber, boyType: boyType})
    return response.data
}

export const getAll = async () => {
    const response = await http.get("/parkinglot/allParkedCars")
    return response.data
}

export const getBoyTypes = async () => {
    const response = await http.get("/parkinglot/parkingBoys")
    return response.data
}