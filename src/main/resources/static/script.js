async function loadResults() {
    try {
        const response = await fetch('/api/test-results');
        const results = await response.json();

        const tableBody = document.getElementById('resultsTableBody');
        const totalTests = document.getElementById('totalTests');
        const passedTests = document.getElementById('passedTests');
        const failedTests = document.getElementById('failedTests');

        tableBody.innerHTML = '';

        let passCount = 0;
        let failCount = 0;

        results.forEach(result => {
            if (result.status === 'PASS') {
                passCount++;
            } else {
                failCount++;
            }

            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${result.id}</td>
                <td>${result.testName}</td>
                <td class="${result.status === 'PASS' ? 'pass-text' : 'fail-text'}">${result.status}</td>
                <td>${result.executionTime}</td>
            `;

            tableBody.appendChild(row);
        });

        totalTests.textContent = results.length;
        passedTests.textContent = passCount;
        failedTests.textContent = failCount;

    } catch (error) {
        console.error('Error loading results:', error);
    }
}

loadResults();