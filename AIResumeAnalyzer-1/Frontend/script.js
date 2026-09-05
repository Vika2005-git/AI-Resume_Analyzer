
const fileInput = document.getElementById("resumeFile");
const analyzeButton = document.getElementById("analyzeBtn");
const resultDiv = document.getElementById("result");

analyzeButton.addEventListener("click", async function () {

    const file = fileInput.files[0];

    if (!file) {
        resultDiv.querySelector("#loadingMessage")?.remove();
        alert("Please select a resume first.");
        return;
    }

    const fileName = file.name.toLowerCase();

    if (!fileName.endsWith(".pdf") && !fileName.endsWith(".docx")) {
        alert("Only PDF and DOCX files are allowed.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {

        analyzeButton.disabled = true;
        analyzeButton.textContent = "Analyzing...";

        let loadingMessage = document.getElementById("loadingMessage");

        if (!loadingMessage) {
            loadingMessage = document.createElement("p");
            loadingMessage.id = "loadingMessage";
            resultDiv.prepend(loadingMessage);
        }

        loadingMessage.textContent =
            "Analyzing resume... Please wait.";

        const response = await fetch(
            "http://localhost:8080/api/resume/upload",
            {
                method: "POST",
                body: formData
            }
        );

        if (!response.ok) {
            throw new Error(
                "Server returned an error: " + response.status
            );
        }

        const data = await response.json();

        console.log(data);

        document.getElementById("atsScore").textContent =
            data.ATSScore;

        document.getElementById("keySkills").textContent =
            data.keySkills;

        document.getElementById("strengths").textContent =
            data.strengths;

        document.getElementById("weaknesses").textContent =
            data.weaknesses;

        document.getElementById("recommendedSkills").textContent =
            data.recommendedSkills;

        document.getElementById("improvementSuggestions").textContent =
            data.improvementSuggestions;

        document.getElementById("overallAssessment").textContent =
            data.overallAssessment;

        loadingMessage.textContent =
            "Resume analyzed successfully!";

    } catch (error) {

        console.error(error);

        const loadingMessage = document.getElementById("loadingMessage");

        if (loadingMessage) {
            loadingMessage.textContent =
                "Unable to analyze resume. Please try again.";
        }

    } finally {

        analyzeButton.disabled = false;
        analyzeButton.textContent = "Analyze Resume";
    }

});
